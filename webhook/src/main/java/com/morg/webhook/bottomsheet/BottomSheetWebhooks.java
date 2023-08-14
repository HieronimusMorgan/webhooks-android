package com.morg.webhook.bottomsheet;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.morg.webhook.R;


public class BottomSheetWebhooks extends BottomSheetDialogFragment {
    private final OnClickBottomSheetWebhooks onClickBottomSheetWebhooks;
    private final String code;

    public BottomSheetWebhooks(String code, OnClickBottomSheetWebhooks onClickBottomSheetWebhooks) {
        this.onClickBottomSheetWebhooks = onClickBottomSheetWebhooks;
        this.code = code;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_RounderUpperCorner_BottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.webhooks_bottom_sheet_nexmitra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton btnConfirm = view.findViewById(R.id.btn_yes);
        MaterialButton btnCancel = view.findViewById(R.id.btn_cancel);
//        TextView tvCode = view.findViewById(R.id.tv_code);
//        ImageView imageView = view.findViewById(R.id.iv_copy);
//        imageView.setOnClickListener(view1 -> {
//            ClipboardManager clipboard = (ClipboardManager) requireActivity()
//                    .getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData clip = ClipData.newPlainText("Copy", code);
//            clipboard.setPrimaryClip(clip);
//            Toast.makeText(requireContext(), "Code ID Copied!", Toast.LENGTH_SHORT).show();
//        });
//        tvCode.setOnLongClickListener(view1 -> {
//            ClipboardManager clipboard = (ClipboardManager) requireActivity()
//                    .getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData clip = ClipData.newPlainText("Copy", code);
//            clipboard.setPrimaryClip(clip);
//            Toast.makeText(requireContext(), "Code ID Copied!", Toast.LENGTH_SHORT).show();
//            return true;
//        });
        btnCancel.setOnClickListener(v -> dismiss());
        btnConfirm.setOnClickListener(v -> {
            onClickBottomSheetWebhooks.onClick(null);
            dismiss();
        });
    }
}
