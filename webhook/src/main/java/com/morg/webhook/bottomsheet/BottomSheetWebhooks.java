package com.morg.webhook.bottomsheet;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.morg.webhook.R;
import com.morg.webhook.databinding.WebhooksBottomSheetNexmitraBinding;


public class BottomSheetWebhooks extends BottomSheetDialogFragment {
    private final String title;
    private final OnClickBottomSheetWebhooks onClickBottomSheetWebhooks;
    private final String description;

    public BottomSheetWebhooks(String title, String description, OnClickBottomSheetWebhooks onClickBottomSheetWebhooks) {
        this.onClickBottomSheetWebhooks = onClickBottomSheetWebhooks;
        this.title = title;
        this.description = description;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.WebhooksBottomSheetStyles);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.webhooks_bottom_sheet_nexmitra, null);
        WebhooksBottomSheetNexmitraBinding binding = WebhooksBottomSheetNexmitraBinding.bind(view);
        if (!title.isEmpty())
            binding.tvTitle.setText(title);
        if (!description.isEmpty())
            binding.tvDescription.setText(description);

        binding.btnCancel.setOnClickListener(v -> dismiss());
        binding.btnYes.setOnClickListener(v -> {
            onClickBottomSheetWebhooks.onClick(null);
            dismiss();
        });
        return view;
    }
}
