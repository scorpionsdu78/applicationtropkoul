package util;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ddprojet.R;

import java.util.List;

public class StringAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int id;
    private List <String>items ;

    public StringAdapter(@NonNull Context context, int textViewResourceId , @NonNull List<String> list) {
        super(context, textViewResourceId , list);
        mContext = context;
        id = textViewResourceId;
        items = list;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.textView);

        if(items.get(position) != null )
        {
            text.setTextColor(Color.WHITE);
            text.setText(items.get(position));
            if(position == 0)
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        }

        return mView;
    }
}
