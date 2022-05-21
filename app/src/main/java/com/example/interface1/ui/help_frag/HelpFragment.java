package com.example.interface1.ui.help_frag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.interface1.CustomArrayAdapter;
import com.example.interface1.ListItemClass;
import com.example.interface1.R;
import com.example.interface1.databinding.FragmentHelpBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelpFragment extends Fragment {


    private HelpViewModel helpViewModel;
    private FragmentHelpBinding binding;
    private Button button;
    final Context context = getActivity();
    private TextView final_text;
    Dialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        helpViewModel =
                new ViewModelProvider(this).get(HelpViewModel.class);

        binding = FragmentHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        button = (Button) root.findViewById(R.id.button2);
        final_text = (TextView) root.findViewById(R.id.final_text);


        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());



        mDialogBuilder
                        .setView(R.layout.prompt)
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //Вводим текст и отображаем в строке ввода на основном экране:
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = mDialogBuilder.create();




        View.OnClickListener cl = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               alertDialog.show();


//                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
//                LayoutInflater li = LayoutInflater.from(context);
//                View promptsView = li.inflate(R.layout.prompt, null);
//
//                //Создаем AlertDialog
//                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
//
//                //Настраиваем prompt.xml для нашего AlertDialog:
//                mDialogBuilder.setView(promptsView);
//
//                //Настраиваем отображение поля для ввода текста в открытом диалоге:
//                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
//
//                //Настраиваем сообщение в диалоговом окне:
//                mDialogBuilder
//                        .setCancelable(false)
//                        .setPositiveButton("OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,int id) {
//                                        //Вводим текст и отображаем в строке ввода на основном экране:
//                                        final_text.setText(userInput.getText());
//                                    }
//                                })
//                        .setNegativeButton("Отмена",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                AlertDialog alertDialog = mDialogBuilder.create();
//
//                //и отображаем его:
//                alertDialog.show();

            }
        };
        button.setOnClickListener(cl);



        helpViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}