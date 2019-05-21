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

    private List<ResultObject> resultObjectList;


    public ResultAdapter(Context context, List<ResultObject> resultObjectList) {
        this.context = context;
        this.resultObjectList = resultObjectList;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_report, parent, false);
        ResultViewHolder quizResultHolder = new ResultViewHolder(layoutView);
        return quizResultHolder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        ResultObject resultObject = resultObjectList.get(position);

        if(resultObject != null){
            holder.quizIndex.setText("QUESTION " + resultObject.getQuizIndex());
            holder.mainQuestion.setText(resultObject.getQuestionTitle().trim());
            holder.correctAnswer.setText("* " + resultObject.getCollectAnswer().trim() + " *");
            holder.yourAnswer.setText("Your answer: " + resultObject.getSelectedAnswer().trim());

            if(resultObject.isCorrect()){
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
        return resultObjectList.size();
    }
}
