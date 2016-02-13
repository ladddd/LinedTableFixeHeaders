package com.ggdg.www.linedtablefixeheaders;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.Random;

import line.LineView;

/**
 * Created by chenweida on 2015/2/6.
 */
public class SampleTableAdapter extends BaseTableAdapter {

    private static final String headers[] = {
            "", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
            "16", "17", "18",
    };

    private static final int ROW_COUNT = 30;
    private static final int COLUMN_COUNT = headers.length;

    private final float density;

    private Context context;
    private int[] dataList;
    private boolean showLine = true;


    public SampleTableAdapter(Context context)
    {
        this.context = context;
        density = context.getResources().getDisplayMetrics().density;

        dataList = new int[ROW_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            Random random = new Random();
            int data = random.nextInt(16) + 3;
            dataList[i] = data;
        }
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
    }

    @Override
    public int getRowCount() {
        return ROW_COUNT;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT - 1;
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {
        final View view;
        switch (getItemViewType(row, column)) {
            case 0:
                view = getFirstHeader(row, column, convertView, parent);
                break;
            case 1:
                view = getHeader(row, column, convertView, parent);
                break;
            case 2:
                view = getFirstBody(row, column, convertView, parent);
                break;
            case 3:
                view = getBody(row, column, convertView, parent);
                break;
            case 4:
                view = getLineView(parent);
                break;
            default:
                throw new RuntimeException("wtf?");
        }
        return view;
    }

    private View getFirstHeader(int row, int column, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_table_header_first, parent, false);
        }
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[0]);
        return convertView;
    }

    private View getHeader(int row, int column, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_table_header, parent, false);
        }
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[column + 1]);
        return convertView;
    }

    private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table_first, parent, false);
        }
        convertView.setBackgroundColor(row % 2 == 0 ? context.getResources().getColor(R.color.row_bg) :
                Color.WHITE);

        View divider = convertView.findViewById(R.id.divide);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);

        divider.setVisibility(View.GONE);
        textView.setTextColor(Color.BLACK);
        textView.setText(String.valueOf(row + 1));

        return convertView;
    }

    private View getBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table, parent, false);
        }
        convertView.setBackgroundColor(row % 2 == 0 ? context.getResources().getColor(R.color.row_bg) :
                Color.WHITE);

        View divider = convertView.findViewById(R.id.divide);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);

        divider.setVisibility(View.GONE);
        textView.setTextColor(Color.BLACK);

        return convertView;
    }

    private View getLineView(ViewGroup parent)
    {
        LineView view = (LineView) LayoutInflater.from(context).inflate(R.layout.line, parent, false);
        //TODO 将行高和列宽设置为一个静态常量
        view.setParam(30, 30, dataList, ROW_COUNT, showLine);
        return view;
    }

    @Override
    public int getWidth(int column) {
        final int width;
        if (column == -1){
            width = 60;
        }
        else {
            width = 30;
        }
        return Math.round(width * density);
    }

    @Override
    public int getHeight(int row) {
        final int height = 30;
        return Math.round(height * density);
    }

    @Override
    public int getItemViewType(int row, int column) {
        final int itemViewType;
        //rowTag是-2， 表示是lineview;
        if (row == -2 && column == -2)
        {
            itemViewType = 4;
        }
        else if (row == -1 && column == -1)
        {
            itemViewType = 0;
        }
        else if (row == -1)
        {
            itemViewType = 1;
        }
        else if (column == -1)
        {
            itemViewType = 2;
        }
        else
        {
            itemViewType = 3;
        }
        return itemViewType;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public boolean hasLineView() {
        return showLine;
    }
}
