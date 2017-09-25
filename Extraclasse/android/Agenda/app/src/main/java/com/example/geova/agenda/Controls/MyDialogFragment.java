package com.example.geova.agenda.Controls;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.example.geova.agenda.Models.Convidado;
import com.example.geova.agenda.Models.Organizador;
import com.example.geova.agenda.R;

import java.text.ParseException;

/**
 * Created by geovane on 19/09/17.
 */

public class MyDialogFragment extends DialogFragment{

    private int layout;
    private Object object;

    private EditText edt_nome;
    private EditText edt_email;
    private EditText edt_telefone;

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
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(validaCampos()){
                            if(layout == R.layout.activity_organizador){
                                object = new Organizador();
                                try {
                                    preencheOrganizador((Organizador)object);
                                } catch (ParseException e) {
                                    object = null;
                                }
                            }
                            if(layout == R.layout.activity_convidado){
                                object = new Convidado();
                                try {
                                    preencheConvidado((Convidado)object);
                                } catch (ParseException e) {
                                    object = null;
                                }
                            }
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

    public void preencheOrganizador(Organizador organizador) throws ParseException {
        organizador.setNome(toText(edt_nome));
        organizador.setEmail(toText(edt_email));
        organizador.setTelefone(toText(edt_telefone));
    }

    public void preencheConvidado(Convidado convidado) throws ParseException {
        convidado.setNome(toText(edt_nome));
        convidado.setEmail(toText(edt_email));
        convidado.setTelefone(toText(edt_telefone));
    }

    public boolean validaCampos() {
        boolean valid = false;

        boolean emptyNome = false;
        boolean emptyEmail = false;
        boolean emptyTelefone = false;

        if(layout == R.layout.activity_organizador){
            edt_nome = (EditText) getDialog().findViewById(R.id.edt_nomeOrganizador);
            edt_email = (EditText) getDialog().findViewById(R.id.edt_emailOrganizador);
            edt_telefone = (EditText) getDialog().findViewById(R.id.edt_telefoneOrganizador);

            emptyNome = TextUtils.isEmpty(toText(edt_nome));
            emptyEmail =  TextUtils.isEmpty(toText(edt_email));
            emptyTelefone =  TextUtils.isEmpty(toText(edt_telefone));
        }
        if(layout == R.layout.activity_convidado){
            edt_nome = (EditText) getDialog().findViewById(R.id.edt_nomeConvidado);
            edt_email = (EditText) getDialog().findViewById(R.id.edt_emailConvidado);
            edt_telefone = (EditText) getDialog().findViewById(R.id.edt_telefoneConvidado);

            emptyNome = TextUtils.isEmpty(toText(edt_nome));
            emptyEmail =  TextUtils.isEmpty(toText(edt_email));
            emptyTelefone =  TextUtils.isEmpty(toText(edt_telefone));
        }

        if(emptyNome) {
            edt_nome.setError(getResources().getString(R.string.erro_Nome));
        }

        if(emptyEmail) {
            edt_email.setError(getResources().getString(R.string.erro_DataInicio));
        }

        if(emptyTelefone) {
            edt_telefone.setError(getResources().getString(R.string.erro_HoraInicio));
        }

        if(!emptyNome && !emptyEmail && !emptyTelefone) {
            valid = true;
        }

        return valid;
    }

    private String toText(EditText editText){
        return editText.getText().toString();
    }
}