package com.example.geovane.divulgar.Fragment;

/**
 * Created by geovane on 29/10/17.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.example.geovane.divulgar.Model.Link;
import com.example.geovane.divulgar.R;

import java.text.ParseException;

public class MyDialogFragment extends DialogFragment {

    private int layout;
    private Object object;

    private EditText edt_nome;
    private EditText edt_url;

    public static MyDialogFragment newInstance(int layout) {
        MyDialogFragment dialog = new MyDialogFragment();
        dialog.setLayout(layout);
        return dialog;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(layout, null))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(validaCampos()){
                            if(layout == R.layout.activity_link){
                                try {
                                    object = preencheLink((Link) object);
                                } catch (ParseException e) {
                                    object = null;
                                }
                            }
                            /*if(layout == R.layout.activity_menu){
                                try {
                                    preencheMenu((Materia)object);
                                } catch (ParseException e) {
                                    object = null;
                                }
                            }*/
                            mListener.onDialogPositiveClick(MyDialogFragment.this, object);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(MyDialogFragment.this);
                    }
                });
        return builder.create();
    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, Object object);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");
        }
    }

    public Link preencheLink(Link link) throws ParseException {
        link = new Link(toText(edt_nome), toText(edt_url));
        return link;
    }

    public boolean validaCampos() {
        boolean valid = false;

        boolean emptyNome = false;
        boolean emptyUrl = false;

        if(layout == R.layout.activity_link){
            edt_nome = (EditText) getDialog().findViewById(R.id.input_link_name);
            edt_url = (EditText) getDialog().findViewById(R.id.input_link_url);

            emptyNome = TextUtils.isEmpty(toText(edt_nome));
            emptyUrl =  TextUtils.isEmpty(toText(edt_url));
        }
        /*if(layout == R.layout.activity_menu){
            edt_nome = (EditText) getDialog().findViewById(R.id.edt_nomeConvidado);
            edt_email = (EditText) getDialog().findViewById(R.id.edt_emailConvidado);
            edt_telefone = (EditText) getDialog().findViewById(R.id.edt_telefoneConvidado);

            emptyNome = TextUtils.isEmpty(toText(edt_nome));
            emptyEmail =  TextUtils.isEmpty(toText(edt_email));
            emptyTelefone =  TextUtils.isEmpty(toText(edt_telefone));
        }*/

        if(emptyNome) {
            edt_nome.setError(getResources().getString(R.string.erro_nome));
        }

        if(emptyUrl) {
            edt_url.setError(getResources().getString(R.string.erro_url));
        }

        if(!emptyNome && !emptyUrl) {
            valid = true;
        }

        return valid;
    }

    private String toText(EditText editText){
        return editText.getText().toString();
    }
}
