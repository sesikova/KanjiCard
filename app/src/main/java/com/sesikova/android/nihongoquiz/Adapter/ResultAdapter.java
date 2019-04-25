package com.sesikova.android.nihongoquiz.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sesikova.android.nihongoquiz.R;
import com.sesikova.android.nihongoquiz.Entity.ResultObject;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder>{

    private static final String TAG = ResultAdapter.class.getSimpleName();

    private Context context;

    private List<ResultObject> resultAnalysis;


    public ResultAdapter(Context context, List<ResultObject> resultAnalysis) {
        this.context = context;
        this.resultAnalysis = resultAnalysis;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_report, parent, false);
        ResultViewHolder quizResultHolder = new ResultViewHolder(layoutView);
        return quizResultHolder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        ResultObject resultData = resultAnalysis.get(position);

        if(resultData != null){
            holder.questionNum.setText("QUESTION " + resultData.getQuestionNumber());
            holder.mainQuestion.setText(resultData.getQuestionTitle().trim());
            holder.correctAnswer.setText("* " + resultData.getCollectAnswer().trim() + " *");
            holder.yourAnswer.setText("Your answer: " + resultData.getSelectedAnswer().trim());

            if(resultData.isCorrect()){
                holder.imageMark.setImageResource(R.drawable.goodmark);
            }else{
                holder.imageMark.setImageResource(R.drawable.badmark);
            }
        }else{
            Toast.makeText(context, "Error!! Quiz result report missing", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return resultAnalysis.size();
    }
}
