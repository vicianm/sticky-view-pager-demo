package com.github.vicianm.stickyviewpager.demo;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.stickyviewpager.demo.validation.Validator;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

/**
 * <code>AppCompatActivity</code> with simple <code>TextView</code> data binding support.
 * @see #bindUiData(View, int, Object, String, Validator)
 */
public class DataBindingActivity extends AppCompatActivity {

    protected Collection<Validator> validators = new LinkedList<>();

    protected void bindUiData(View container, int containerFieldId, final Object dataObject, final String dataObjectFieldName, final Validator validator) {

        validators.add(validator);

        TextView textView = (TextView)container.findViewById(containerFieldId);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // Bind data
                try {
                    Field declaredField = dataObject.getClass().getDeclaredField(dataObjectFieldName);
                    declaredField.setAccessible(true);
                    declaredField.set(dataObject, s.toString());
                } catch (Throwable t) {
                    Log.e("DataBindingActivity", "Data binding failed", t);
                }

                // Validate data
                if (validator != null) {
                    validator.validate();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Executes all validators bound to the form.
     */
    protected void validateForm() {
        for (Validator validator : validators) {
            validator.validate();
        }
    }

}
