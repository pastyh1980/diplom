package com.github.vkpeb.validator;

import com.github.vkpeb.model.form.ParentForm;
import com.github.vkpeb.model.form.StudentForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by pasty on 06.05.2016.
 */
@Component
public class StudFormValidator implements Validator {

    private Pattern onlyCyrSymbols = Pattern.compile("^[а-яА-я]+$");

    private Pattern dateFormat = Pattern.compile("^((0[1-9])|([12][0-9])|(3[01]))\\.((0[1-9])|(1[0-2]))\\.((19[3-9][0-9])|(20[01][0-9]))$");

    @Override
    public boolean supports(Class<?> aClass) {
        return StudentForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StudentForm studentForm = (StudentForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studFamily", "notEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studName", "notEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studBorn", "notEmpty");

        if (!studentForm.getStudFamily().isEmpty() && !onlyCyrSymbols.matcher(studentForm.getStudFamily()).matches())
            errors.rejectValue("studFamily", "incorrectNameFormat");

        if (!studentForm.getStudName().isEmpty() && !onlyCyrSymbols.matcher(studentForm.getStudName()).matches())
            errors.rejectValue("studName", "incorrectNameFormat");

        if (!studentForm.getStudOtchestvo().isEmpty() && !onlyCyrSymbols.matcher(studentForm.getStudOtchestvo()).matches())
            errors.rejectValue("studOtchestvo", "incorrectNameFormat");

        if (!studentForm.getStudBorn().isEmpty() && !dateFormat.matcher(studentForm.getStudBorn()).matches())
            errors.rejectValue("studBorn", "incorrectDateFormat");

        for (int i = 0; studentForm.getParents() != null && i < studentForm.getParents().size(); ++i) {
            ParentForm parentForm = studentForm.getParents().get(i);

            if (parentForm.getFamily().isEmpty())
                errors.rejectValue("parents[" + i + "].family", "notEmpty");
            else if (!onlyCyrSymbols.matcher(parentForm.getFamily()).matches())
                errors.rejectValue("parents[" + i + "].family", "incorrectNameFormat");

            if (parentForm.getName().isEmpty())
                errors.rejectValue("parents[" + i + "].name", "notEmpty");
            else if (!onlyCyrSymbols.matcher(parentForm.getName()).matches())
                errors.rejectValue("parents[" + i + "].name", "incorrectNameFormat");

            if (!parentForm.getOtchestvo().isEmpty() && !onlyCyrSymbols.matcher(parentForm.getOtchestvo()).matches())
                errors.rejectValue("parents[" + i + "].otchestvo", "incorrectNameFormat");
        }
    }
}
