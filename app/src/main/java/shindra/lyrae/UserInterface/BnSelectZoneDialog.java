package shindra.lyrae.UserInterface;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import shindra.lyrae.BattleNetApi.AccountHandler.BnAccountHandler;
import shindra.lyrae.R;

public class BnSelectZoneDialog extends DialogFragment{

    private @BnAccountHandler.BnAccountZone int zoneSelected;
    private BnSelectZoneDialogListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_tittle_choose_account_zone);
        listener = (BnSelectZoneDialogListener) getActivity();
        builder.setSingleChoiceItems(R.array.bn_account_zone, -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                zoneSelected = which +1;
                listener.zoneSelected(zoneSelected);
                dismiss();
            }
        });

        d = builder.create();
        d.setCanceledOnTouchOutside(false);
        return  d;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
           // listener = (BnSelectZoneDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement BnSelectZoneDialogListener");
        }

    }


    public interface BnSelectZoneDialogListener{
        void zoneSelected(@BnAccountHandler.BnAccountZone int zoneSelected);
    }
}
