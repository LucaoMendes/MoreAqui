package com.projFg.moreaqui.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Grupo 11
 * GitHub:https://github.com/LucaoMendes/MoreAqui
 * Trello:https://trello.com/b/XstseyJW/moreaqui
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */

public class Mask {
    public static final String FORMAT_FONE = "(##)#####-####";
    public static TextWatcher mask(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = Mask.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
    }
}
