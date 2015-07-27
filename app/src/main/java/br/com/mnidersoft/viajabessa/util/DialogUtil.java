package br.com.mnidersoft.viajabessa.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.ViewGroup;

import br.com.mnidersoft.viajabessa.R;

/**
 * Classe criada por mim e que mantenho no Github. Gerencio todos os dialogs com ela
 */
public class DialogUtil {

	private static DialogUtil instance;

    private static boolean isWaiting = false;

	private static Dialog dialog;
	private Context context;

	private DialogUtil(){}

	public static synchronized DialogUtil getInstance(Context context) {
		if(instance == null) {
			instance = new DialogUtil();
		}
        if(instance.context != context && context != null) {
            instance.context = context;
        }
		return instance;
	}

    public Dialog getDialog() {
        return this.dialog;
    }

	public void dismiss() {
		if(this.dialog != null && this.dialog.isShowing()) {
			this.dialog.dismiss();
			this.dialog = null;
            isWaiting = false;
		}
	}
	
	public void makeAlert(String title, String message, OnClickListener listener) {
		this.dismiss();
		this.dialog = new AlertDialog.Builder(this.context)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton(this.context.getString(android.R.string.ok), listener)
		.show();
        isWaiting = false;
	}

	public void makeAlert(String title, String message) {
		this.makeAlert(title, message, null);
	}
	
	public void makeAlert(String message, OnClickListener listener) {
		this.makeAlert(this.context.getString(R.string.warning), message, listener);
	}
	
	public void makeAlert(String message) {
		this.makeAlert(this.context.getString(R.string.warning), message, null);
	}
	
	public void makeAlert(int messageId, OnClickListener listener) {
		this.dismiss();
		this.dialog = new AlertDialog.Builder(this.context)
		.setTitle(this.context.getString(R.string.warning))
		.setMessage(messageId)
		.setPositiveButton(this.context.getString(android.R.string.ok), listener)
		.show();
        isWaiting = false;
	}

	public void makeAlert(int messageId) {
		this.makeAlert(messageId, null);
	}

    public void makeConfirm(int messageId, OnClickListener yesListener) {
        this.makeConfirm(this.context.getString(R.string.warning), this.context.getString(messageId), yesListener);
    }

	public void makeConfirm(String title, String message, OnClickListener yesListener) {
		this.dismiss();
		this.dialog = new AlertDialog.Builder(this.context)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton(this.context.getString(R.string.yes), yesListener)
		.setNegativeButton(this.context.getString(R.string.no), null)
		.show();
        isWaiting = false;
	}
	
	public void makeConfirm(String message, OnClickListener yesListener) {
		this.makeConfirm(this.context.getString(R.string.warning), message, yesListener);
	}
	
	public void showContent(int titleId, View view, boolean cancelable) {
		this.dismiss();

		AlertDialog alert = new AlertDialog.Builder(this.context)
		.setCancelable(cancelable)
		.setTitle(titleId)
		.create();
		alert.setView(view, 0, 0, 0, 0);
		this.dialog = alert;
		this.dialog.show();

		this.setOnDismissListener(view);
        isWaiting = false;
	}
	
	/**
	 * Shows a custom View with has a custom title and can be cancelable
	 * @param title Title of your dialog
	 * @param view View of your dialog
	 * @param cancelable If your dialog can be cancelable
	 */
	public void showContent(String title, View view, boolean cancelable) {
		this.dismiss();

		AlertDialog alert = new AlertDialog.Builder(this.context)
		.setCancelable(cancelable)
		.setTitle(title)
		.create();
		alert.setView(view, 0, 0, 0, 0);
		this.dialog = alert;
		this.dialog.show();

		this.setOnDismissListener(view);
        isWaiting = false;
	}
	
	/**
	 * Remove all views when dialog is dismissing. This is important because of "the specified child already has a parent" exception
	 * @param view View of your dialog
	 */
	private void setOnDismissListener(final View view) {
		this.dialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				ViewGroup parent = (ViewGroup)view.getParent();
				parent.removeAllViews();
			}
		});
	}
	
	public void showContent(View view) {
		this.showContent(view, true);
	}
	
	public void showContent(View view, boolean cancelable) {
		this.dismiss();
		this.dialog = new AlertDialog.Builder(this.context)
		.setTitle(R.string.select)
		.setCancelable(cancelable)
		.setView(view)
		.show();
        isWaiting = false;
	}
	
	public void showWaiting() {
		this.showWaiting(this.context.getString(R.string.pleaseWait), false);
	}
	
	public void showWaiting(boolean cancelable) {
		this.showWaiting(this.context.getString(R.string.pleaseWait), cancelable);
	}
	
	public void showWaiting(String message, boolean cancelable) {
		this.dismiss();
		this.dialog = ProgressDialog.show(this.context, "", message, true, cancelable);
		this.dialog.show();
        isWaiting = true;
	}
	
	public void showWaiting(String message) {
		this.showWaiting(message, false);
	}
	
	public void showWaiting(int messageId, boolean cancelable) {
		this.dismiss();
		this.dialog = ProgressDialog.show(this.context, "", this.context.getString(messageId), true, cancelable);
		this.dialog.show();
        isWaiting = true;
	}
	
	public void showWaiting(int messageId) {
		this.showWaiting(messageId, false);
	}

    public ProgressDialog makeProgressDialog(int messageId, boolean inderteminate, boolean cancelable) {
        this.dismiss();
        ProgressDialog progressBar = new ProgressDialog(this.context);
        progressBar.setCancelable(cancelable);
        progressBar.setMessage(this.context.getString(messageId));
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setIndeterminate(inderteminate);
        this.dialog = progressBar;
        isWaiting = true;
        return progressBar;
    }

    public ProgressDialog makeProgressDialog(int messageId, boolean inderteminate) {
        return this.makeProgressDialog(messageId, inderteminate, false);
    }

    public ProgressDialog makeProgressDialog(int messageId) {
        return this.makeProgressDialog(messageId, false, false);
    }

	public boolean isShowing() {
		return this.dialog.isShowing();
	}
}