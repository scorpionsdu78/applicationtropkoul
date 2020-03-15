package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ddprojet.R;
import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.model.Alignment;
import com.example.ddprojet.model.Character;

public class DescriptionFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.description_layout, container, false);

        final Spinner ali1 = v.findViewById(R.id.al1);
        final Spinner ali2 = v.findViewById(R.id.al2);
        final TextView name = v.findViewById(R.id.namePerso);
        final TextView backGround = v.findViewById(R.id.backgound);
        final TextView characterTrait = v.findViewById(R.id.character_trait);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.array_lawfulChaoticAxis, android.R.layout.simple_spinner_item);
        ali1.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(v.getContext(),R.array.array_goodEvilAxis, android.R.layout.simple_spinner_item);
        ali2.setAdapter(adapter1);

        Button btn = (Button) v.findViewById(R.id.validation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation(ali1.getSelectedItem().toString(),ali2.getSelectedItem().toString(),name.getText().toString(),backGround.getText().toString(),characterTrait.getText().toString());
            }
        });


        return v;
    }

    public void validation(String spin1, String spin2, String name, String background, String trait){
        CharacterEditionActivity activity = (CharacterEditionActivity) getActivity();
        Character character = activity.getCharacter();

        character.setAlignment(new Alignment(spin1,spin2));
        character.setName(name);
        character.setPersonality_traits(trait);
        character.setBackground(background);

        activity.submit(v);

    }
}
