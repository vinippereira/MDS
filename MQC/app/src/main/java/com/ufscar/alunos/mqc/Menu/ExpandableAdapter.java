//package com.ufscar.alunos.mqc.Menu;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.TextView;
//
//import com.ufscar.alunos.mqc.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * Created by lucasbocanegra on 25/11/15.
// */
//public class ExpandableAdapter  extends BaseExpandableListAdapter{
//
//    private ArrayList<String> listgroup;
//    private HashMap<String, ArrayList<String>> listData;
//    private LayoutInflater inflater;
//
//
//    public ExpandableAdapter(Context context, ArrayList<String> listgroup, HashMap<String, ArrayList<String>> listData) {
//        this.listgroup = listgroup;
//        this.listData = listData;
//        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getGroupCount() {
//        return listgroup.size();
//    }
//
//    @Override
//    public int getChildrenCount(int i) {
//        return listData.get(listgroup.get(i)).size();
//    }
//
//    @Override
//    public Object getGroup(int i) {
//        return listData.get(listgroup.get(i));
//    }
//
//    @Override
//    public Object getChild(int i, int i1) {
//        return  listData.get(listgroup.get(i)).get(i1);
//    }
//
//    @Override
//    public long getGroupId(int i) {
//        return i;
//    }
//
//    @Override
//    public long getChildId(int i, int i1) {
//        return i1;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
//        ViewHolderGroup holder;
//
//        if(view == null){
//            view = inflater.inflate(R.layout.header_expandable_list_view, null);
//            holder = new ViewHolderGroup();
//            view.setTag(holder);
//
//            holder.tvGroup = (TextView) view.findViewById(R.id.tvGroup);
//        }else {
//            holder = (ViewHolderGroup) view.getTag();
//        }
//
//        holder.tvGroup.setText(listgroup.get(i));
//
//        return view;
//    }
//
//    @Override
//    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
//        ViewHolderItem holder;
//        String val = (String) getChild(i, i1);
//
//        if(view == null){
//            view = inflater.inflate(R.layout.item_expandable_list_view, null);
//            holder = new ViewHolderItem();
//            view.setTag(holder);
//
//            holder.tvItem = (TextView) view.findViewById(R.id.tvItem);
//        }else {
//            holder = (ViewHolderItem) view.getTag();
//        }
//
//        holder.tvItem.setText(val);
//
//
//        return view;
//    }
//
//    @Override
//    public boolean isChildSelectable(int i, int i1) {
//        return true;
//    }
//
//    class ViewHolderGroup{
//        TextView tvGroup;
//    }
//    class ViewHolderItem{
//        TextView tvItem;
//    }
//}
