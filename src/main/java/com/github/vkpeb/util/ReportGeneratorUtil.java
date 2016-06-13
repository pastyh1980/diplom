package com.github.vkpeb.util;

import com.github.vkpeb.model.*;
import com.github.vkpeb.model.enumer.FamilyStatuses;
import com.github.vkpeb.model.enumer.Month;
import com.github.vkpeb.model.form.FirstSemestrVisiting;
import com.github.vkpeb.model.form.ReportCardForm;
import com.github.vkpeb.model.form.SecondSemestrVisiting;
import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pasty on 01.06.2016.
 */
@Component
public class ReportGeneratorUtil {

    private static String filepath = "report_templates/";

    public static WordprocessingMLPackage generateMeetingReport(ParentMeetings meeting, List<Student> studentList, Boss boss) throws Docx4JException, IOException{
        String group = studentList.get(0).getGroup().getGroupName();
        Resource resource = new ClassPathResource(filepath + "meeting.docx");
        WordprocessingMLPackage wordMLPackage = Docx4J.load(resource.getFile());
        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
        Map<String, String> placeholderMap = new HashMap<>();
        placeholderMap.put("group", group);
        placeholderMap.put("datetime", new SimpleDateFormat("dd.MM.yyyy HH:mm").format(meeting.getDate()));
        placeholderMap.put("theme", meeting.getTheme());
        placeholderMap.put("boss", boss.getFamily() + " " + boss.getName() + " " + boss.getOtchestvo());

        List<Object> elements = getAllElementFromObject(mainDocumentPart, Text.class);

        for (Object element : elements){
            Text textElement = (Text) element;
            if (placeholderMap.containsKey(textElement.getValue())){
                textElement.setValue(placeholderMap.get(textElement.getValue()));
            }
        }

        List<Object> tableElements = getAllElementFromObject(mainDocumentPart, Tbl.class);

        Tbl table = (Tbl) tableElements.get(0);
        if (table.getContent().size() > 1) {
            int size = table.getContent().size();
            for (int i = size - 1 ; i > 0 ; --i)
                table.getContent().remove(i);
        }
        for (Student student : studentList) {
            Tr row = new Tr();
            Tc cell1 = new Tc();
            P paragraph  = new P();
            R run = new R();
            RPr rPr = new RPr();
            RFonts rFonts = new RFonts();
            rFonts.setAscii("Times New Roman");
            rFonts.setHAnsi("Times New Roman");
            rFonts.setCs("Times New Roman");
            rPr.setRFonts(rFonts);
            HpsMeasure hpsMeasure = new HpsMeasure();
            hpsMeasure.setVal(new BigInteger("24"));
            rPr.setSz(hpsMeasure);
            rPr.setSzCs(hpsMeasure);
            run.setRPr(rPr);
            Text studentStr = new Text();
            studentStr.setValue(student.getFamily() + " " + student.getName() + " " + student.getOtchestvo());
            run.getContent().add(studentStr);
            paragraph.getContent().add(run);
            cell1.getContent().add(paragraph);
            row.getContent().add(cell1);
            for (int i = 0 ; i < 3 ; ++i) {
                Tc cell = new Tc();
                P paragraph1  = new P();
                R run1 = new R();
                run1.setRPr(rPr);
                run1.getContent().add(new Text());
                paragraph1.getContent().add(run1);
                cell.getContent().add(paragraph1);
                row.getContent().add(cell);
            }
            table.getContent().add(row);
        }


        return wordMLPackage;
    }

    public static WordprocessingMLPackage socialPassport(Group group,
                                                         Integer total_count,
                                                         Integer fem_count,
                                                         Integer men_count,
                                                         Integer dormitory,
                                                         Integer apartment,
                                                         Integer rent,
                                                         Integer cottage,
                                                         Integer high_m,
                                                         Integer high_f,
                                                         Integer ss_m,
                                                         Integer ss_f,
                                                         Integer s_m,
                                                         Integer s_f,
                                                         Integer ns_m,
                                                         Integer ns_f,
                                                         Map<FamilyStatuses, List<Student>> studentMap,
                                                         Map<Long, List<Parent>> parentMap) throws Docx4JException, IOException {

        Resource resource = new ClassPathResource(filepath + "socpassport.docx");
        WordprocessingMLPackage wordMLPackage = Docx4J.load(resource.getFile());
        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
        String boss = group.getBoss().getFamily() + " " + group.getBoss().getName() + " " + group.getBoss().getOtchestvo();
        Map<String, String> placeholderMap = new HashMap<>();
        placeholderMap.put("group", group.getGroupName());
        placeholderMap.put("boss", boss);
        placeholderMap.put("total", String.valueOf(total_count));
        placeholderMap.put("fem", String.valueOf(fem_count));
        placeholderMap.put("men", String.valueOf(men_count));
        placeholderMap.put("dormitory", String.valueOf(dormitory));
        placeholderMap.put("apartment", String.valueOf(apartment));
        placeholderMap.put("rent", String.valueOf(rent));
        placeholderMap.put("cottage", String.valueOf(cottage));
        placeholderMap.put("highm", String.valueOf(high_m));
        placeholderMap.put("highf", String.valueOf(high_f));
        placeholderMap.put("ssm", String.valueOf(ss_m));
        placeholderMap.put("ssf", String.valueOf(ss_f));
        placeholderMap.put("sm", String.valueOf(s_m));
        placeholderMap.put("sf", String.valueOf(s_f));
        placeholderMap.put("nsm", String.valueOf(ns_m));
        placeholderMap.put("nsf", String.valueOf(ns_f));

        List<Object> elements = getAllElementFromObject(mainDocumentPart, Text.class);

        for (Object element : elements){
            Text textElement = (Text) element;
            if (placeholderMap.containsKey(textElement.getValue())){
                textElement.setValue(placeholderMap.get(textElement.getValue()));
            }
        }

        List<Object> tableElements = getAllElementFromObject(mainDocumentPart, Tbl.class);

        Tbl table = (Tbl) tableElements.get(0);
        if (table.getContent().size() > 1) {
            int size = table.getContent().size();
            for (int i = size - 1 ; i > 0 ; --i)
                table.getContent().remove(i);
        }

        TcPr vMergedCell = new TcPr();
        TcPrInner.VMerge vMerge = new TcPrInner.VMerge();
        vMerge.setVal("Vertical merged");
        vMergedCell.setVMerge(vMerge);

        RPr rPr = new RPr();
        RFonts rFonts = new RFonts();
        rFonts.setAscii("Times New Roman");
        rFonts.setHAnsi("Times New Roman");
        rFonts.setCs("Times New Roman");
        rPr.setRFonts(rFonts);
        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(new BigInteger("24"));
        rPr.setSz(hpsMeasure);
        rPr.setSzCs(hpsMeasure);

        Tr row = new Tr();

        for (Map.Entry<FamilyStatuses, List<Student>> entry : studentMap.entrySet()) {
            Tc cell1 = new Tc();
            //cell1.setTcPr(vMergedCell);
            P paragraph  = new P();
            R run = new R();
            run.setRPr(rPr);
            Text status = new Text();
            status.setValue(entry.getKey().getRus());
            run.getContent().add(status);
            paragraph.getContent().add(run);
            cell1.getContent().add(paragraph);
            row.getContent().add(cell1);

            boolean firstStud = true;

            for (Student student : entry.getValue()) {
                if (firstStud) {
                    firstStud = false;
                } else {
                    cell1.setTcPr(vMergedCell);
                    cell1 = new Tc();
                    cell1.setTcPr(vMergedCell);
                    P paragraph1  = new P();
                    R run1 = new R();
                    run1.setRPr(rPr);
                    paragraph1.getContent().add(run1);
                    cell1.getContent().add(paragraph1);
                    row.getContent().add(cell1);
                }

                Tc cell2 = new Tc();
                P paragraph2  = new P();
                R run2 = new R();
                run2.setRPr(rPr);
                Text fio = new Text();
                fio.setValue(student.getFamily() + " " + student.getName() + " " + student.getOtchestvo());
                run2.getContent().add(fio);
                paragraph2.getContent().add(run2);
                cell2.getContent().add(paragraph2);
                row.getContent().add(cell2);

                Tc cell3 = new Tc();
                P paragraph3  = new P();
                R run3 = new R();
                run3.setRPr(rPr);
                Text born = new Text();
                born.setValue(student.getBorn());
                run3.getContent().add(born);
                paragraph3.getContent().add(run3);
                cell3.getContent().add(paragraph3);
                row.getContent().add(cell3);

                String parents = "";
                boolean first = true;

                for (Parent parent : parentMap.get(student.getId())) {
                    if (first) {
                        parents += parent.getFamily() + " " + parent.getName() + " " + parent.getOtchestvo();
                        first = false;
                    } else {
                        parents += ", " + parent.getFamily() + " " + parent.getName() + " " + parent.getOtchestvo();
                    }
                }

                Tc cell4 = new Tc();
                P paragraph4  = new P();
                R run4 = new R();
                run4.setRPr(rPr);
                Text par = new Text();
                par.setValue(parents);
                run4.getContent().add(par);
                paragraph4.getContent().add(run4);
                cell4.getContent().add(paragraph4);
                row.getContent().add(cell4);

                Tc cell5 = new Tc();
                P paragraph5  = new P();
                R run5 = new R();
                run5.setRPr(rPr);
                Text addrphone = new Text();
                addrphone.setValue(student.getAddress() + ", тел: " + student.getPhone());
                run5.getContent().add(addrphone);
                paragraph5.getContent().add(run5);
                cell5.getContent().add(paragraph5);
                row.getContent().add(cell5);

                table.getContent().add(row);

                row = new Tr();
            }

            if (entry.getValue().isEmpty()) {
                Tc cell2 = new Tc();
                P paragraph2  = new P();
                R run2 = new R();
                run2.setRPr(rPr);
                paragraph2.getContent().add(run2);
                cell2.getContent().add(paragraph2);
                row.getContent().add(cell2);

                Tc cell3 = new Tc();
                P paragraph3  = new P();
                R run3 = new R();
                run3.setRPr(rPr);
                paragraph3.getContent().add(run3);
                cell3.getContent().add(paragraph3);
                row.getContent().add(cell3);

                Tc cell4 = new Tc();
                P paragraph4  = new P();
                R run4 = new R();
                run4.setRPr(rPr);
                paragraph4.getContent().add(run4);
                cell4.getContent().add(paragraph4);
                row.getContent().add(cell4);

                Tc cell5 = new Tc();
                P paragraph5  = new P();
                R run5 = new R();
                run5.setRPr(rPr);
                paragraph5.getContent().add(run5);
                cell5.getContent().add(paragraph5);
                row.getContent().add(cell5);

                table.getContent().add(row);

                row = new Tr();
            }
        }

        return wordMLPackage;
    }

    public static WordprocessingMLPackage firstSemestrVisiting(List<FirstSemestrVisiting> firstSemestrVisitingList,
                                                               FirstSemestrVisiting total,
                                                               String group) throws Docx4JException, IOException {
        Resource resource = new ClassPathResource(filepath + "firstsemestrvisiting.docx");
        WordprocessingMLPackage wordMLPackage = Docx4J.load(resource.getFile());
        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();

        List<Object> elements = getAllElementFromObject(mainDocumentPart, Text.class);

        for (Object element : elements){
            Text textElement = (Text) element;
            if (textElement.getValue().equals("group")) {
                textElement.setValue(group);
            }
        }

        List<Object> tableElements = getAllElementFromObject(mainDocumentPart, Tbl.class);

        Tbl table = (Tbl) tableElements.get(0);
        if (table.getContent().size() > 2) {
            int size = table.getContent().size();
            for (int i = size - 1 ; i > 1 ; --i)
                table.getContent().remove(i);
        }

        RPr rPr = new RPr();
        RFonts rFonts = new RFonts();
        rFonts.setAscii("Times New Roman");
        rFonts.setHAnsi("Times New Roman");
        rFonts.setCs("Times New Roman");
        rPr.setRFonts(rFonts);
        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(new BigInteger("20"));
        rPr.setSz(hpsMeasure);
        rPr.setSzCs(hpsMeasure);

        for (FirstSemestrVisiting visiting : firstSemestrVisitingList) {
            Tr row = new Tr();

            Tc cell1 = new Tc();
            P paragraph1 = new P();
            R run1 = new R();
            run1.setRPr(rPr);
            Text number = new Text();
            number.setValue(String.valueOf(visiting.getNumber()));
            run1.getContent().add(number);
            paragraph1.getContent().add(run1);
            cell1.getContent().add(paragraph1);
            row.getContent().add(cell1);

            Tc cell2 = new Tc();
            P paragraph2 = new P();
            R run2 = new R();
            run2.setRPr(rPr);
            Text fio = new Text();
            fio.setValue(String.valueOf(visiting.getStudFio()));
            run2.getContent().add(fio);
            paragraph2.getContent().add(run2);
            cell2.getContent().add(paragraph2);
            row.getContent().add(cell2);

            Tc cell3 = new Tc();
            P paragraph3 = new P();
            R run3 = new R();
            run3.setRPr(rPr);
            Text septemberValid = new Text();
            septemberValid.setValue(String.valueOf(visiting.getSeptemberValid()));
            run3.getContent().add(septemberValid);
            paragraph3.getContent().add(run3);
            cell3.getContent().add(paragraph3);
            row.getContent().add(cell3);

            Tc cell4 = new Tc();
            P paragraph4 = new P();
            R run4 = new R();
            run4.setRPr(rPr);
            Text septemberInvalid = new Text();
            septemberInvalid.setValue(String.valueOf(visiting.getSeptemberInvalid()));
            run4.getContent().add(septemberInvalid);
            paragraph4.getContent().add(run4);
            cell4.getContent().add(paragraph4);
            row.getContent().add(cell4);

            Tc cell5 = new Tc();
            P paragraph5 = new P();
            R run5 = new R();
            run5.setRPr(rPr);
            Text octoberValid = new Text();
            octoberValid.setValue(String.valueOf(visiting.getOctoberValid()));
            run5.getContent().add(octoberValid);
            paragraph5.getContent().add(run5);
            cell5.getContent().add(paragraph5);
            row.getContent().add(cell5);

            Tc cell6 = new Tc();
            P paragraph6 = new P();
            R run6 = new R();
            run6.setRPr(rPr);
            Text octoberInvalid = new Text();
            octoberInvalid.setValue(String.valueOf(visiting.getOctoberInvalid()));
            run6.getContent().add(octoberInvalid);
            paragraph6.getContent().add(run6);
            cell6.getContent().add(paragraph6);
            row.getContent().add(cell6);

            Tc cell7 = new Tc();
            P paragraph7 = new P();
            R run7 = new R();
            run7.setRPr(rPr);
            Text novemberValid = new Text();
            novemberValid.setValue(String.valueOf(visiting.getNovemberValid()));
            run7.getContent().add(novemberValid);
            paragraph7.getContent().add(run7);
            cell7.getContent().add(paragraph7);
            row.getContent().add(cell7);

            Tc cell8 = new Tc();
            P paragraph8 = new P();
            R run8 = new R();
            run8.setRPr(rPr);
            Text novemberInvalid = new Text();
            novemberInvalid.setValue(String.valueOf(visiting.getNovemberInvalid()));
            run8.getContent().add(novemberInvalid);
            paragraph8.getContent().add(run8);
            cell8.getContent().add(paragraph8);
            row.getContent().add(cell8);

            Tc cell9 = new Tc();
            P paragraph9 = new P();
            R run9 = new R();
            run9.setRPr(rPr);
            Text decemberValid = new Text();
            decemberValid.setValue(String.valueOf(visiting.getDecemberValid()));
            run9.getContent().add(decemberValid);
            paragraph9.getContent().add(run9);
            cell9.getContent().add(paragraph9);
            row.getContent().add(cell9);

            Tc cell10 = new Tc();
            P paragraph10 = new P();
            R run10 = new R();
            run10.setRPr(rPr);
            Text decemberInvalid = new Text();
            decemberInvalid.setValue(String.valueOf(visiting.getDecemberInvalid()));
            run10.getContent().add(decemberInvalid);
            paragraph10.getContent().add(run10);
            cell10.getContent().add(paragraph10);
            row.getContent().add(cell10);

            Tc cell11 = new Tc();
            P paragraph11 = new P();
            R run11 = new R();
            run11.setRPr(rPr);
            Text totalValid = new Text();
            totalValid.setValue(String.valueOf(visiting.getTotalValid()));
            run11.getContent().add(totalValid);
            paragraph11.getContent().add(run11);
            cell11.getContent().add(paragraph11);
            row.getContent().add(cell11);

            Tc cell12 = new Tc();
            P paragraph12 = new P();
            R run12 = new R();
            run12.setRPr(rPr);
            Text totalInvalid = new Text();
            totalInvalid.setValue(String.valueOf(visiting.getTotalInvalid()));
            run12.getContent().add(totalInvalid);
            paragraph12.getContent().add(run12);
            cell12.getContent().add(paragraph12);
            row.getContent().add(cell12);

            table.getContent().add(row);
        }

        Tr row = new Tr();

        TcPr hMergedCell = new TcPr();
        TcPrInner.GridSpan gridSpan = new TcPrInner.GridSpan();
        gridSpan.setVal(new BigInteger("2"));
        hMergedCell.setGridSpan(gridSpan);

        Tc cell1 = new Tc();
        cell1.setTcPr(hMergedCell);
        P paragraph1 = new P();
        R run1 = new R();
        run1.setRPr(rPr);
        Text number = new Text();
        number.setValue("Итого:");
        run1.getContent().add(number);
        paragraph1.getContent().add(run1);
        cell1.getContent().add(paragraph1);
        row.getContent().add(cell1);

        Tc cell3 = new Tc();
        P paragraph3 = new P();
        R run3 = new R();
        run3.setRPr(rPr);
        Text septemberValid = new Text();
        septemberValid.setValue(String.valueOf(total.getSeptemberValid()));
        run3.getContent().add(septemberValid);
        paragraph3.getContent().add(run3);
        cell3.getContent().add(paragraph3);
        row.getContent().add(cell3);

        Tc cell4 = new Tc();
        P paragraph4 = new P();
        R run4 = new R();
        run4.setRPr(rPr);
        Text septemberInvalid = new Text();
        septemberInvalid.setValue(String.valueOf(total.getSeptemberInvalid()));
        run4.getContent().add(septemberInvalid);
        paragraph4.getContent().add(run4);
        cell4.getContent().add(paragraph4);
        row.getContent().add(cell4);

        Tc cell5 = new Tc();
        P paragraph5 = new P();
        R run5 = new R();
        run5.setRPr(rPr);
        Text octoberValid = new Text();
        octoberValid.setValue(String.valueOf(total.getOctoberValid()));
        run5.getContent().add(octoberValid);
        paragraph5.getContent().add(run5);
        cell5.getContent().add(paragraph5);
        row.getContent().add(cell5);

        Tc cell6 = new Tc();
        P paragraph6 = new P();
        R run6 = new R();
        run6.setRPr(rPr);
        Text octoberInvalid = new Text();
        octoberInvalid.setValue(String.valueOf(total.getOctoberInvalid()));
        run6.getContent().add(octoberInvalid);
        paragraph6.getContent().add(run6);
        cell6.getContent().add(paragraph6);
        row.getContent().add(cell6);

        Tc cell7 = new Tc();
        P paragraph7 = new P();
        R run7 = new R();
        run7.setRPr(rPr);
        Text novemberValid = new Text();
        novemberValid.setValue(String.valueOf(total.getNovemberValid()));
        run7.getContent().add(novemberValid);
        paragraph7.getContent().add(run7);
        cell7.getContent().add(paragraph7);
        row.getContent().add(cell7);

        Tc cell8 = new Tc();
        P paragraph8 = new P();
        R run8 = new R();
        run8.setRPr(rPr);
        Text novemberInvalid = new Text();
        novemberInvalid.setValue(String.valueOf(total.getNovemberInvalid()));
        run8.getContent().add(novemberInvalid);
        paragraph8.getContent().add(run8);
        cell8.getContent().add(paragraph8);
        row.getContent().add(cell8);

        Tc cell9 = new Tc();
        P paragraph9 = new P();
        R run9 = new R();
        run9.setRPr(rPr);
        Text decemberValid = new Text();
        decemberValid.setValue(String.valueOf(total.getDecemberValid()));
        run9.getContent().add(decemberValid);
        paragraph9.getContent().add(run9);
        cell9.getContent().add(paragraph9);
        row.getContent().add(cell9);

        Tc cell10 = new Tc();
        P paragraph10 = new P();
        R run10 = new R();
        run10.setRPr(rPr);
        Text decemberInvalid = new Text();
        decemberInvalid.setValue(String.valueOf(total.getDecemberInvalid()));
        run10.getContent().add(decemberInvalid);
        paragraph10.getContent().add(run10);
        cell10.getContent().add(paragraph10);
        row.getContent().add(cell10);

        Tc cell11 = new Tc();
        P paragraph11 = new P();
        R run11 = new R();
        run11.setRPr(rPr);
        Text totalValid = new Text();
        totalValid.setValue(String.valueOf(total.getTotalValid()));
        run11.getContent().add(totalValid);
        paragraph11.getContent().add(run11);
        cell11.getContent().add(paragraph11);
        row.getContent().add(cell11);

        Tc cell12 = new Tc();
        P paragraph12 = new P();
        R run12 = new R();
        run12.setRPr(rPr);
        Text totalInvalid = new Text();
        totalInvalid.setValue(String.valueOf(total.getTotalInvalid()));
        run12.getContent().add(totalInvalid);
        paragraph12.getContent().add(run12);
        cell12.getContent().add(paragraph12);
        row.getContent().add(cell12);

        table.getContent().add(row);

        return wordMLPackage;
    }

    public static WordprocessingMLPackage secondSemestrVisiting(List<SecondSemestrVisiting> secondSemestrVisitings,
                                                                SecondSemestrVisiting total,
                                                               String group) throws Docx4JException, IOException {
        Resource resource = new ClassPathResource(filepath + "secondsemestrvisiting.docx");
        WordprocessingMLPackage wordMLPackage = Docx4J.load(resource.getFile());
        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();

        List<Object> elements = getAllElementFromObject(mainDocumentPart, Text.class);

        for (Object element : elements){
            Text textElement = (Text) element;
            if (textElement.getValue().equals("group")) {
                textElement.setValue(group);
            }
        }

        List<Object> tableElements = getAllElementFromObject(mainDocumentPart, Tbl.class);

        Tbl table = (Tbl) tableElements.get(0);
        if (table.getContent().size() > 2) {
            int size = table.getContent().size();
            for (int i = size - 1 ; i > 1 ; --i)
                table.getContent().remove(i);
        }

        RPr rPr = new RPr();
        RFonts rFonts = new RFonts();
        rFonts.setAscii("Times New Roman");
        rFonts.setHAnsi("Times New Roman");
        rFonts.setCs("Times New Roman");
        rPr.setRFonts(rFonts);
        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(new BigInteger("20"));
        rPr.setSz(hpsMeasure);
        rPr.setSzCs(hpsMeasure);

        for (SecondSemestrVisiting visiting : secondSemestrVisitings) {
            Tr row = new Tr();

            Tc cell1 = new Tc();
            P paragraph1 = new P();
            R run1 = new R();
            run1.setRPr(rPr);
            Text number = new Text();
            number.setValue(String.valueOf(visiting.getNumber()));
            run1.getContent().add(number);
            paragraph1.getContent().add(run1);
            cell1.getContent().add(paragraph1);
            row.getContent().add(cell1);

            Tc cell2 = new Tc();
            P paragraph2 = new P();
            R run2 = new R();
            run2.setRPr(rPr);
            Text fio = new Text();
            fio.setValue(String.valueOf(visiting.getStudFio()));
            run2.getContent().add(fio);
            paragraph2.getContent().add(run2);
            cell2.getContent().add(paragraph2);
            row.getContent().add(cell2);

            Tc cell3 = new Tc();
            P paragraph3 = new P();
            R run3 = new R();
            run3.setRPr(rPr);
            Text januaryValid = new Text();
            januaryValid.setValue(String.valueOf(visiting.getJanuaryValid()));
            run3.getContent().add(januaryValid);
            paragraph3.getContent().add(run3);
            cell3.getContent().add(paragraph3);
            row.getContent().add(cell3);

            Tc cell4 = new Tc();
            P paragraph4 = new P();
            R run4 = new R();
            run4.setRPr(rPr);
            Text januaryInvalid = new Text();
            januaryInvalid.setValue(String.valueOf(visiting.getJanuaryInvalid()));
            run4.getContent().add(januaryInvalid);
            paragraph4.getContent().add(run4);
            cell4.getContent().add(paragraph4);
            row.getContent().add(cell4);

            Tc cell5 = new Tc();
            P paragraph5 = new P();
            R run5 = new R();
            run5.setRPr(rPr);
            Text februaryValid = new Text();
            februaryValid.setValue(String.valueOf(visiting.getFebruaryValid()));
            run5.getContent().add(februaryValid);
            paragraph5.getContent().add(run5);
            cell5.getContent().add(paragraph5);
            row.getContent().add(cell5);

            Tc cell6 = new Tc();
            P paragraph6 = new P();
            R run6 = new R();
            run6.setRPr(rPr);
            Text februaryInvalid = new Text();
            februaryInvalid.setValue(String.valueOf(visiting.getFebruaryInvalid()));
            run6.getContent().add(februaryInvalid);
            paragraph6.getContent().add(run6);
            cell6.getContent().add(paragraph6);
            row.getContent().add(cell6);

            Tc cell7 = new Tc();
            P paragraph7 = new P();
            R run7 = new R();
            run7.setRPr(rPr);
            Text martValid = new Text();
            martValid.setValue(String.valueOf(visiting.getMartValid()));
            run7.getContent().add(martValid);
            paragraph7.getContent().add(run7);
            cell7.getContent().add(paragraph7);
            row.getContent().add(cell7);

            Tc cell8 = new Tc();
            P paragraph8 = new P();
            R run8 = new R();
            run8.setRPr(rPr);
            Text martInvalid = new Text();
            martInvalid.setValue(String.valueOf(visiting.getMartInvalid()));
            run8.getContent().add(martInvalid);
            paragraph8.getContent().add(run8);
            cell8.getContent().add(paragraph8);
            row.getContent().add(cell8);

            Tc cell9 = new Tc();
            P paragraph9 = new P();
            R run9 = new R();
            run9.setRPr(rPr);
            Text aprilValid = new Text();
            aprilValid.setValue(String.valueOf(visiting.getAprilValid()));
            run9.getContent().add(aprilValid);
            paragraph9.getContent().add(run9);
            cell9.getContent().add(paragraph9);
            row.getContent().add(cell9);

            Tc cell10 = new Tc();
            P paragraph10 = new P();
            R run10 = new R();
            run10.setRPr(rPr);
            Text aprilInvalid = new Text();
            aprilInvalid.setValue(String.valueOf(visiting.getAprilInvalid()));
            run10.getContent().add(aprilInvalid);
            paragraph10.getContent().add(run10);
            cell10.getContent().add(paragraph10);
            row.getContent().add(cell10);

            Tc cell11 = new Tc();
            P paragraph11 = new P();
            R run11 = new R();
            run11.setRPr(rPr);
            Text mayValid = new Text();
            mayValid.setValue(String.valueOf(visiting.getMayValid()));
            run11.getContent().add(mayValid);
            paragraph11.getContent().add(run11);
            cell11.getContent().add(paragraph11);
            row.getContent().add(cell11);

            Tc cell12 = new Tc();
            P paragraph12 = new P();
            R run12 = new R();
            run12.setRPr(rPr);
            Text mayInvalid = new Text();
            mayInvalid.setValue(String.valueOf(visiting.getMayInvalid()));
            run12.getContent().add(mayInvalid);
            paragraph12.getContent().add(run12);
            cell12.getContent().add(paragraph12);
            row.getContent().add(cell12);

            Tc cell13 = new Tc();
            P paragraph13 = new P();
            R run13 = new R();
            run13.setRPr(rPr);
            Text totalValid = new Text();
            totalValid.setValue(String.valueOf(visiting.getTotalValid()));
            run13.getContent().add(totalValid);
            paragraph13.getContent().add(run13);
            cell13.getContent().add(paragraph13);
            row.getContent().add(cell13);

            Tc cell14 = new Tc();
            P paragraph14 = new P();
            R run14 = new R();
            run14.setRPr(rPr);
            Text totalInvalid = new Text();
            totalInvalid.setValue(String.valueOf(visiting.getTotalInvalid()));
            run14.getContent().add(totalInvalid);
            paragraph14.getContent().add(run14);
            cell14.getContent().add(paragraph14);
            row.getContent().add(cell14);

            table.getContent().add(row);
        }

        Tr row = new Tr();

        TcPr hMergedCell = new TcPr();
        TcPrInner.GridSpan gridSpan = new TcPrInner.GridSpan();
        gridSpan.setVal(new BigInteger("2"));
        hMergedCell.setGridSpan(gridSpan);

        Tc cell1 = new Tc();
        cell1.setTcPr(hMergedCell);
        P paragraph1 = new P();
        R run1 = new R();
        run1.setRPr(rPr);
        Text number = new Text();
        number.setValue("Итого:");
        run1.getContent().add(number);
        paragraph1.getContent().add(run1);
        cell1.getContent().add(paragraph1);
        row.getContent().add(cell1);

        Tc cell3 = new Tc();
        P paragraph3 = new P();
        R run3 = new R();
        run3.setRPr(rPr);
        Text januaryValid = new Text();
        januaryValid.setValue(String.valueOf(total.getJanuaryValid()));
        run3.getContent().add(januaryValid);
        paragraph3.getContent().add(run3);
        cell3.getContent().add(paragraph3);
        row.getContent().add(cell3);

        Tc cell4 = new Tc();
        P paragraph4 = new P();
        R run4 = new R();
        run4.setRPr(rPr);
        Text januaryInvalid = new Text();
        januaryInvalid.setValue(String.valueOf(total.getJanuaryInvalid()));
        run4.getContent().add(januaryInvalid);
        paragraph4.getContent().add(run4);
        cell4.getContent().add(paragraph4);
        row.getContent().add(cell4);

        Tc cell5 = new Tc();
        P paragraph5 = new P();
        R run5 = new R();
        run5.setRPr(rPr);
        Text februaryValid = new Text();
        februaryValid.setValue(String.valueOf(total.getFebruaryValid()));
        run5.getContent().add(februaryValid);
        paragraph5.getContent().add(run5);
        cell5.getContent().add(paragraph5);
        row.getContent().add(cell5);

        Tc cell6 = new Tc();
        P paragraph6 = new P();
        R run6 = new R();
        run6.setRPr(rPr);
        Text februaryInvalid = new Text();
        februaryInvalid.setValue(String.valueOf(total.getFebruaryInvalid()));
        run6.getContent().add(februaryInvalid);
        paragraph6.getContent().add(run6);
        cell6.getContent().add(paragraph6);
        row.getContent().add(cell6);

        Tc cell7 = new Tc();
        P paragraph7 = new P();
        R run7 = new R();
        run7.setRPr(rPr);
        Text martValid = new Text();
        martValid.setValue(String.valueOf(total.getMartValid()));
        run7.getContent().add(martValid);
        paragraph7.getContent().add(run7);
        cell7.getContent().add(paragraph7);
        row.getContent().add(cell7);

        Tc cell8 = new Tc();
        P paragraph8 = new P();
        R run8 = new R();
        run8.setRPr(rPr);
        Text martInvalid = new Text();
        martInvalid.setValue(String.valueOf(total.getMartInvalid()));
        run8.getContent().add(martInvalid);
        paragraph8.getContent().add(run8);
        cell8.getContent().add(paragraph8);
        row.getContent().add(cell8);

        Tc cell9 = new Tc();
        P paragraph9 = new P();
        R run9 = new R();
        run9.setRPr(rPr);
        Text aprilValid = new Text();
        aprilValid.setValue(String.valueOf(total.getAprilValid()));
        run9.getContent().add(aprilValid);
        paragraph9.getContent().add(run9);
        cell9.getContent().add(paragraph9);
        row.getContent().add(cell9);

        Tc cell10 = new Tc();
        P paragraph10 = new P();
        R run10 = new R();
        run10.setRPr(rPr);
        Text aprilInvalid = new Text();
        aprilInvalid.setValue(String.valueOf(total.getAprilInvalid()));
        run10.getContent().add(aprilInvalid);
        paragraph10.getContent().add(run10);
        cell10.getContent().add(paragraph10);
        row.getContent().add(cell10);

        Tc cell11 = new Tc();
        P paragraph11 = new P();
        R run11 = new R();
        run11.setRPr(rPr);
        Text mayValid = new Text();
        mayValid.setValue(String.valueOf(total.getMayValid()));
        run11.getContent().add(mayValid);
        paragraph11.getContent().add(run11);
        cell11.getContent().add(paragraph11);
        row.getContent().add(cell11);

        Tc cell12 = new Tc();
        P paragraph12 = new P();
        R run12 = new R();
        run12.setRPr(rPr);
        Text mayInvalid = new Text();
        mayInvalid.setValue(String.valueOf(total.getMayInvalid()));
        run12.getContent().add(mayInvalid);
        paragraph12.getContent().add(run12);
        cell12.getContent().add(paragraph12);
        row.getContent().add(cell12);

        Tc cell13 = new Tc();
        P paragraph13 = new P();
        R run13 = new R();
        run13.setRPr(rPr);
        Text totalValid = new Text();
        totalValid.setValue(String.valueOf(total.getTotalValid()));
        run13.getContent().add(totalValid);
        paragraph13.getContent().add(run13);
        cell13.getContent().add(paragraph13);
        row.getContent().add(cell13);

        Tc cell14 = new Tc();
        P paragraph14 = new P();
        R run14 = new R();
        run14.setRPr(rPr);
        Text totalInvalid = new Text();
        totalInvalid.setValue(String.valueOf(total.getTotalInvalid()));
        run14.getContent().add(totalInvalid);
        paragraph14.getContent().add(run14);
        cell14.getContent().add(paragraph14);
        row.getContent().add(cell14);

        table.getContent().add(row);

        return wordMLPackage;
    }

    public static WordprocessingMLPackage reportCard(Month month, Integer year, List<Discipline> disciplineList, List<Student> studentList,
                                                     ReportCardForm reportCardForm, Map<Long, Integer> totalMiss,
                                                     Map<Long, Integer> validMiss, Map<Long, Integer> invalidMiss) throws Docx4JException, IOException{
        Resource resource = new ClassPathResource(filepath + "reportcard.docx");
        WordprocessingMLPackage wordMLPackage = Docx4J.load(resource.getFile());
        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
        int number = 0;
        String group = studentList.get(0).getGroup().getGroupName();

        Map<String, String> placeholderMap = new HashMap<>();
        placeholderMap.put("month", month.name());
        placeholderMap.put("year", String.valueOf(year));
        placeholderMap.put("group", group);

        List<Object> elements = getAllElementFromObject(mainDocumentPart, Text.class);

        for (Object element : elements){
            Text textElement = (Text) element;
            if (placeholderMap.containsKey(textElement.getValue())){
                textElement.setValue(placeholderMap.get(textElement.getValue()));
            }
        }

        List<Object> tableElements = getAllElementFromObject(mainDocumentPart, Tbl.class);

        Tbl table = (Tbl) tableElements.get(0);
        if (table.getContent().size() > 1) {
            int size = table.getContent().size();
            for (int i = size - 1 ; i > 0 ; --i)
                table.getContent().remove(i);
        }

        table.getTblPr().getTblLayout().setType(STTblLayoutType.AUTOFIT);

        Tr row = (Tr) table.getContent().get(0);

        RPr rPr = new RPr();
        RFonts rFonts = new RFonts();
        rFonts.setAscii("Times New Roman");
        rFonts.setHAnsi("Times New Roman");
        rFonts.setCs("Times New Roman");
        rPr.setRFonts(rFonts);
        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(new BigInteger("20"));
        rPr.setSz(hpsMeasure);
        rPr.setSzCs(hpsMeasure);

        TcPr verticalCell = new TcPr();
        TextDirection textdirection = new TextDirection();
        verticalCell.setTextDirection(textdirection);
        textdirection.setVal("btLr");
        TblWidth tblwidth = new TblWidth();
        verticalCell.setTcW(tblwidth);
        tblwidth.setType("dxa");
        tblwidth.setW( BigInteger.valueOf(437) );

        for (Discipline discipline : disciplineList) {
            Tc cell = new Tc();
            cell.setTcPr(verticalCell);
            P paragraph = new P();
            R run = new R();
            run.setRPr(rPr);
            Text discName = new Text();
            discName.setValue(discipline.getDisciplineName());
            run.getContent().add(discName);
            paragraph.getContent().add(run);
            cell.getContent().add(paragraph);
            row.getContent().add(cell);
        }

        Tc cell1 = new Tc();
        cell1.setTcPr(verticalCell);
        P paragraph1 = new P();
        R run1 = new R();
        run1.setRPr(rPr);
        Text tMiss = new Text();
        tMiss.setValue("Всего ч.");
        run1.getContent().add(tMiss);
        paragraph1.getContent().add(run1);
        cell1.getContent().add(paragraph1);
        row.getContent().add(cell1);

        Tc cell2 = new Tc();
        cell2.setTcPr(verticalCell);
        P paragraph2 = new P();
        R run2 = new R();
        run2.setRPr(rPr);
        Text vMiss = new Text();
        vMiss.setValue("Уважительные");
        run2.getContent().add(vMiss);
        paragraph2.getContent().add(run2);
        cell2.getContent().add(paragraph2);
        row.getContent().add(cell2);

        Tc cell3 = new Tc();
        cell3.setTcPr(verticalCell);
        P paragraph3 = new P();
        R run3 = new R();
        run3.setRPr(rPr);
        Text iMiss = new Text();
        iMiss.setValue("Неуважительные");
        run3.getContent().add(iMiss);
        paragraph3.getContent().add(run3);
        cell3.getContent().add(paragraph3);
        row.getContent().add(cell3);

        Map<Long, ReportCardForm.Row> rates = reportCardForm.getRows();

        for (Student student : studentList) {
            row = new Tr();

            Tc newCell1 = new Tc();
            P newParagraph1 = new P();
            R newRun1 = new R();
            newRun1.setRPr(rPr);
            Text num = new Text();
            num.setValue(String.valueOf(++number));
            newRun1.getContent().add(num);
            newParagraph1.getContent().add(newRun1);
            newCell1.getContent().add(newParagraph1);
            row.getContent().add(newCell1);

            Tc newCell2 = new Tc();
            P newParagraph2 = new P();
            R newRun2 = new R();
            newRun2.setRPr(rPr);
            Text studFio = new Text();
            studFio.setValue(student.getFamily() + " " + student.getName() + " " + student.getOtchestvo());
            newRun2.getContent().add(studFio);
            newParagraph2.getContent().add(newRun2);
            newCell2.getContent().add(newParagraph2);
            row.getContent().add(newCell2);

            for (Discipline discipline : disciplineList) {
                String rate = rates.get(student.getId()).get(discipline.getDisciplineName()).getRate();

                Tc rateCell = new Tc();
                P rateParagraph = new P();
                R rateRun = new R();
                rateRun.setRPr(rPr);
                Text rateText = new Text();
                rateText.setValue(rate);
                rateRun.getContent().add(rateText);
                rateParagraph.getContent().add(rateRun);
                rateCell.getContent().add(rateParagraph);
                row.getContent().add(rateCell);
            }

            Tc newCell3 = new Tc();
            P newParagraph3 = new P();
            R newRun3 = new R();
            newRun3.setRPr(rPr);
            Text miss1 = new Text();
            miss1.setValue(String.valueOf(totalMiss.get(student.getId())));
            newRun3.getContent().add(miss1);
            newParagraph3.getContent().add(newRun3);
            newCell3.getContent().add(newParagraph3);
            row.getContent().add(newCell3);

            Tc newCell4 = new Tc();
            P newParagraph4 = new P();
            R newRun4 = new R();
            newRun4.setRPr(rPr);
            Text miss2 = new Text();
            miss2.setValue(String.valueOf(validMiss.get(student.getId())));
            newRun4.getContent().add(miss2);
            newParagraph4.getContent().add(newRun4);
            newCell4.getContent().add(newParagraph4);
            row.getContent().add(newCell4);

            Tc newCell5 = new Tc();
            P newParagraph5 = new P();
            R newRun5 = new R();
            newRun5.setRPr(rPr);
            Text miss3 = new Text();
            miss3.setValue(String.valueOf(invalidMiss.get(student.getId())));
            newRun5.getContent().add(miss3);
            newParagraph5.getContent().add(newRun5);
            newCell5.getContent().add(newParagraph5);
            row.getContent().add(newCell5);

            table.getContent().add(row);
        }

        return wordMLPackage;
    }

    private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<>();
        if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();

        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }

        }
        return result;
    }
}

