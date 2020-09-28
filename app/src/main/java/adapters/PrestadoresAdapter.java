package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pruebanativa.R;

import java.util.List;
import java.util.Properties;

import modelos.Citas;

public class PrestadoresAdapter extends BaseAdapter {

    List<Properties> citas;
    Context context;

    public PrestadoresAdapter(Context context, List<Properties> citas){

        this.context = context;
        this.citas = citas;


    }

    @Override
    public int getCount() {
        return citas.size();
    }

    @Override
    public Object getItem(int position) {
        return citas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(citas.get(position).getProperty("cod"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_adapter, parent, false);
        }
        return convertView;

    }
}
