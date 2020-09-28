package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pruebanativa.R;

import java.util.List;

import modelos.Citas;

public class GridAdapter extends BaseAdapter {

    List<Citas> citas;
    Context context;

    public GridAdapter(Context context,List<Citas> citas){

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
        return citas.get(position).getCod_usuario_prestador();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_grid, parent, false);
        }
        return convertView;

    }
}
