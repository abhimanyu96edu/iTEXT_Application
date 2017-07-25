package com.abhimanyusharma.resumetemplatesapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Template1Activity extends AppCompatActivity {

    private TextView tv;

    String u_userEmail = "abhimanyusharma96.edu@gmail.com",
            u_achievement = "Founder and CTO of Resume Inc. and Developed Resume Inc. App",
            u_profileImage,

    u_co_description = "To Pursue Advanced Software and Game Development Career",

    u_e_college = "AKTU",
            u_e_course = "B.Tech ",
            u_e_marks = "%%%%%%",
            u_e_start_date = "2017",
            u_e_end_date = "2017",

    u_name = "Resume Inc.",
            u_email = "abhimanyusharma96.edu@gmail.com",
            u_number = "+91-9999999999",
            u_address, u_year_branch = "4th Year, Computer Science Engineering, Galgotias College of Engineering",
            u_portfolio = "http://abhimanyu96edu.github.io",
            u_linkedin = "www.github.io/abhimanyu96edu",

    u_pe_title = "Resume Inc. App",
            u_pe_description = "It is an Offline Super Duper, Fast, Easy to Use, Instant Resume Builder",
            u_pe_link = "http://www.github.com/abhimanyu96edu/resume_inc.",
            u_pe_end_date = "JULY-2017",

    u_ts_description = "Android Development",

    u_w_designation = "CTO, Worked at ",
            u_w_organization = "Resume Inc.",
            u_w_description = "CTO and Founder of Resume Inc.",
            u_w_start_date = "JUN-2017",
            u_w_end_date = "JULY-2017",
            u_radioGroup = "Worked";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template1);

        tv = (TextView) findViewById(R.id.tv);

        requestPermissions();

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

        String FILE = Environment.getExternalStorageDirectory().toString() + "/PDF/Template1_Resume.pdf";

// Create New Blank Document
        Document document = new Document(PageSize.A4);

    // Create Pdf Writer for Writting into New Created Document
        try {

            PdfWriter.getInstance(document, new FileOutputStream(FILE));

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        try {
            addMetaData(document);
            addTitle(document);
            addContent(document);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


// Close Document after writting all content
        document.close();

        tv.setText("RESUME PDF Successfully Created at: " + FILE);

        Toast.makeText(getApplicationContext(), "Your Resume Is Saved At Location: \n" + FILE, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Thank You for Using RESUME INC.", Toast.LENGTH_SHORT).show();

    }

    private void addMetaData(Document document) {

        document.addTitle("Resume Inc.");
        document.addSubject("Resume Inc.");
        document.addKeywords("Personal, Education, Skills, Work, Resume, Technical, Achievements");
        document.addAuthor("Abhimanyu Sharma");
        document.addCreator("Abhimanyu Sharma");
    }

    private void addTitle(Document document) throws DocumentException, IOException {

        document.newPage();

        BaseColor myColor1 = new BaseColor(2, 177, 178);
        BaseColor myColor2 = new BaseColor(95, 96, 108);

        //Font nameFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, myColor1);
        //Font descFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, myColor2);
//_____________________________________________________
        Paragraph row1 = new Paragraph();
        row1.setFont(new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, myColor1));
        row1.setAlignment(Element.ALIGN_CENTER);
        row1.setLeading(15, 0);
        row1.add(u_name);

        Paragraph row2 = new Paragraph();
        row2.setFont(new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, myColor2));
        row2.setAlignment(Element.ALIGN_CENTER);
        row2.add(u_co_description);

        PdfPCell row_1 = new PdfPCell(row1);
        PdfPCell row_2 = new PdfPCell(row2);

        row_1.setBorder(Rectangle.NO_BORDER);
        row_2.setBorder(Rectangle.NO_BORDER);

        //Add table Rows
        PdfPTable th = new PdfPTable(1);
        th.setWidthPercentage(100.0f);

        th.addCell(row_1);
        th.addCell(row_2);

        InputStream inputStream = getAssets().open("logo.png");
        Bitmap bmp = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        //picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image logoImage = Image.getInstance(stream.toByteArray());
        //logoImage.scalePercent(60);
        logoImage.scaleAbsolute(80, 80);

        PdfPTable tH = new PdfPTable(3);
        tH.setWidthPercentage(100.0f);

        tH.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        tH.setWidths(new int[]{7, 1, 2});
        tH.addCell(th);
        tH.addCell(String.valueOf(new Chunk("")));
        tH.addCell(logoImage);

        document.add(tH);

        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTableGray(1));

    }

    private void addContent(Document document) throws DocumentException, IOException {


        BaseColor myColor1 = new BaseColor(2, 177, 178);//GREENY
        BaseColor myColor2 = new BaseColor(95, 96, 108);//GRAYY

        //Font allHeadingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, myColor1);
        //Font headingFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE, BaseColor.GRAY);
        //Font specialFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, myColor2);
        Font normalBoldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, myColor2);
        Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.GRAY);
        //Font smallBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.DARK_GRAY);

        Paragraph emailBar = new Paragraph();
        emailBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.NORMAL, myColor1));
        emailBar.setAlignment(Element.ALIGN_CENTER);
        emailBar.add(u_email);

        Paragraph numberBar = new Paragraph();
        numberBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.NORMAL, myColor1));
        numberBar.setAlignment(Element.ALIGN_CENTER);
        numberBar.add(u_number);

        Paragraph portfolioBar = new Paragraph();
        portfolioBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.NORMAL, myColor1));
        portfolioBar.setAlignment(Element.ALIGN_CENTER);
        portfolioBar.add(u_portfolio);

        Paragraph linkedinBar = new Paragraph();
        linkedinBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.NORMAL, myColor1));
        linkedinBar.setAlignment(Element.ALIGN_CENTER);
        linkedinBar.add(u_linkedin);

        PdfPCell c1 = new PdfPCell(emailBar);
        PdfPCell c2 = new PdfPCell(numberBar);
        PdfPCell c3 = new PdfPCell(linkedinBar);
        PdfPCell c4 = new PdfPCell(linkedinBar);

        c1.setBorder(Rectangle.NO_BORDER);
        c2.setBorder(Rectangle.NO_BORDER);
        c3.setBorder(Rectangle.NO_BORDER);
        c4.setBorder(Rectangle.NO_BORDER);

        //Add table Rows
        PdfPTable tbar = new PdfPTable(4);
        tbar.setWidthPercentage(100.0f);
        tbar.setWidths(new int[] {2, 1, 2, 2});

        tbar.addCell(c1);
        tbar.addCell(c2);
        tbar.addCell(c3);
        tbar.addCell(c4);

        document.add(tbar);

        document.add(addColorLineEmptyTableGray(1));
        document.add(addEmptyLine(1));

// MAIN PAGE START_________________________________________________________



        //BIGGEST TABLE
        PdfPTable bigT = new PdfPTable(2);
        bigT.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        bigT.setWidthPercentage(100.0f);

        //TABLE 1 FOR BIGGEST TABLE
        PdfPTable bigT1 = new PdfPTable(1);
        bigT1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        bigT1.setWidthPercentage(100.0f);

        //TABLE 2 FOR BIGGEST TABLE
        PdfPTable bigT2 = new PdfPTable(1);
        bigT2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        bigT2.setWidthPercentage(100.0f);

        //bigT.add(bigT1);
        //bigT.add(bigT2);

    //________________________________________________________________________
    //WORK EXPERIENCE

        bigT1.addCell(addEmptyLine(1));
        bigT1.addCell(addHeadTitle("Work Experience"));
        bigT1.addCell(addEmptyLine(1));

        Paragraph pw1 = new Paragraph();
        pw1.setFont(normalBoldFont);
        //pw1.add(String.valueOf(i + 1) + ". " + wed.getW_designation() + ", " + status + " at " + wed.getW_organization());
        pw1.add("\u2022 " + u_w_designation +  u_w_organization);
        pw1.setAlignment(Element.ALIGN_LEFT);
        //document.add(pw1);

        Paragraph pw2 = new Paragraph();
        pw2.setFont(smallFont);
        pw2.add(" " + u_w_start_date + " \u2014 " + u_w_end_date);
        pw2.setAlignment(Element.ALIGN_LEFT);
        //document.add(pw2);

        Paragraph pw3 = new Paragraph();
        pw3.setFont(normalFont);
        pw3.add(" " + u_w_description);
        pw3.setAlignment(Element.ALIGN_LEFT);
        //document.add(pe2);

        PdfPCell w1 = new PdfPCell(pw1);
        PdfPCell w2 = new PdfPCell(pw2);
        PdfPCell w3 = new PdfPCell(pw3);

        w1.setBorder(Rectangle.NO_BORDER);
        w2.setBorder(Rectangle.NO_BORDER);
        w3.setBorder(Rectangle.NO_BORDER);

        //Add table Rows
        PdfPTable wbar = new PdfPTable(1);
        wbar.setWidthPercentage(100.0f);

        wbar.addCell(w1);
        wbar.addCell(w2);
        wbar.addCell(w3);


        bigT1.addCell(wbar);
        //ADDING DATA TO BIGTABLE 1

    //________________________________________________________________________
    //PROJECT EXPERIENCE


        bigT1.addCell(addColorLineEmptyTable(1));
        bigT1.addCell(addEmptyLine(1));
        bigT1.addCell(addHeadTitle("Project Experience"));
        bigT1.addCell(addEmptyLine(1));

        //java.util.List<ProjectExperienceData> list2 = ProjectExperienceData.getProjectExperienceValue(u_userEmail);

        //for (int i = 0; i < list2.size(); i++) {
        //    ProjectExperienceData ped = list2.get(i);

        Paragraph pe1 = new Paragraph();
        pe1.setFont(normalBoldFont);
        //pe1.add(String.valueOf(i + 1) + ". " + ped.getPe_title() + "  \u2014  " + ped.getPe_link() + "     " + "(" + ped.getPe_end_date() + ")");
        pe1.add("\u2022 " + u_pe_title + "     " + "(" + u_pe_end_date + ")");
        pe1.setAlignment(Element.ALIGN_LEFT);
        //document.add(pe1);

        Paragraph pe2 = new Paragraph();
        pe2.setFont(smallFont);
        pe2.add(" " + u_pe_link );
        pe2.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe3 = new Paragraph();
        pe3.setFont(normalFont);
        //pe3.add("     " + ped.getPe_description());
        pe3.add(" " + u_pe_description);
        pe3.setAlignment(Element.ALIGN_LEFT);
        //document.add(pe2);

        PdfPCell p1 = new PdfPCell(pe1);
        PdfPCell p2 = new PdfPCell(pe2);
        PdfPCell p3 = new PdfPCell(pe3);

        p1.setBorder(Rectangle.NO_BORDER);
        p2.setBorder(Rectangle.NO_BORDER);
        p3.setBorder(Rectangle.NO_BORDER);

        //Add table Rows
        PdfPTable pebar = new PdfPTable(1);
        pebar.setWidthPercentage(100.0f);

        pebar.addCell(p1);
        pebar.addCell(p2);
        pebar.addCell(p3);

        bigT1.addCell(pebar);

                    //ADDING BIGT1 TABLE DATA TO BIGT TABLE
        bigT.addCell(bigT1);
                    //DATA ADDED TO BIGGEST TABLE

    //________________________________________________________________________
    //EDUCATION

        bigT2.addCell(addColorLineEmptyTable(1));
        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Education"));
        bigT2.addCell(addEmptyLine(1));

                /*
        java.util.List<EducationData> list1 = EducationData.getEducationValue(u_userEmail);
        //list.setSymbolIndent(42);
        for (int i = 0; i < list1.size(); i++) {
            EducationData ed = list1.get(i);
            //ad.getAchievementValue();
            //lt.add(new ListItem());

            table.addCell(ed.getE_college());
            table.addCell(ed.getE_course());
            table.addCell(ed.getE_marks());
            table.addCell(ed.getE_end_date());
        }
*/
        //Generate Table Contents
        Paragraph e1 = new Paragraph();
        e1.setFont(normalFont);
        //pe1.add(String.valueOf(i + 1) + ". " + ped.getPe_title() + "  \u2014  " + ped.getPe_link() + "     " + "(" + ped.getPe_end_date() + ")");
        e1.add("\u2022 " + u_e_course + " from " + u_e_college + "\n");
        e1.add( " (" + u_e_end_date + ")" + "\n");
        e1.add(" Scored " + u_e_marks + " marks " + "\n");
        e1.setAlignment(Element.ALIGN_LEFT);

        PdfPTable ebar = new PdfPTable(1);
        ebar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        ebar.setWidthPercentage(100.0f);

        ebar.addCell(e1);

        bigT2.addCell(ebar);

    //________________________________________________________________________
    //SKILLS

        bigT2.addCell(addColorLineEmptyTable(1));
        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Skills and Competences"));
        bigT2.addCell(addEmptyLine(1));

        /*        java.util.List<TechnicalSkillsData> list4 = TechnicalSkillsData.getTs_descriptionValue(u_userEmail);
        //list.setSymbolIndent(42);


        for (int i = 0; i < list4.size(); i++) {
            TechnicalSkillsData tsd = list4.get(i);
            //ad.getAchievementValue();
            //lt.add(new ListItem());
            //lt4.add(new ListItem(tsd.getTs_description()));
*/
        Paragraph prSkills = new Paragraph();
        prSkills.setFont(normalFont);
        prSkills.add("•  " + u_ts_description);
        prSkills.setAlignment(Element.ALIGN_LEFT);

        PdfPTable sbar = new PdfPTable(1);
        sbar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        sbar.setWidthPercentage(100.0f);

        sbar.addCell(prSkills);

        bigT2.addCell(sbar);

    //________________________________________________________________________
    //ACHIEVEMENTS

        bigT2.addCell(addColorLineEmptyTable(1));
        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Achievements"));
        bigT2.addCell(addEmptyLine(1));

        /*
        java.util.List<AchievementData> list5 = AchievementData.getAchievementValue(u_userEmail);
        //list.setSymbolIndent(42);
        for (int i = 0; i < list5.size(); i++) {
            AchievementData ad = list5.get(i);
            //ad.getAchievementValue();
            //lt.add(new ListItem());
            //lt5.add(new ListItem(ad.getAchievement()));
*/
        Paragraph prAchievements = new Paragraph();
        prAchievements.setFont(normalFont);
        //prAchievements.add("\u2022  " + ad.getAchievement());
        prAchievements.add("\u2022  " + u_achievement);
        prAchievements.setAlignment(Element.ALIGN_LEFT);

        PdfPTable abar = new PdfPTable(1);
        abar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        abar.setWidthPercentage(100.0f);

        abar.addCell(prAchievements);

        bigT2.addCell(abar);

    //ALL DATA ADDED, NOW ADDIND BIGT2 TABLES TO BIGT TABLE

        bigT.addCell(bigT2);

        document.add(bigT);

                   /*____________________*/
        document.newPage();
                   /*____________________*/
    }


//_____________________________________________________________________________________________//

    public Paragraph addEmptyLine(int lines) {

        int i;
        //Add Empty Line
        Paragraph emptyLines = new Paragraph();

        for (i = 0; i < lines; i++) {
            emptyLines.add(new Paragraph(" "));
        }
        return emptyLines;
    }

    public PdfPTable addColorLineEmptyTable(int lines) {
        int i;

        // Create Table into Document with
        PdfPTable myTable = new PdfPTable(lines);
        for (i = 0; i < lines; i++) {

            myTable.setWidthPercentage(100.0f);
            PdfPCell myCell = new PdfPCell(new Paragraph(""));
            myCell.setBorder(Rectangle.BOTTOM);
            myCell.setBorderColor(new BaseColor(2, 177, 178));

            // Add Cell into Table
            myTable.addCell(myCell);
        }
        return myTable;
    }

    public void requestPermissions() {
        Log.d("RESULT", "----------------------------------requestPermissions: ");
        ActivityCompat.requestPermissions(Template1Activity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), ChooseTemplateActivity.class));
        finish();
    }

    private Paragraph addHeadTitle(String s) {

        BaseColor myColor2 = new BaseColor(2, 177, 178);//GREENY
        Font allHeadingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.UNDERLINE| Font.BOLD, myColor2);
        Paragraph prHead = new Paragraph();
        prHead.setFont(allHeadingFont);
        prHead.add(s);

        prHead.setAlignment(Element.ALIGN_LEFT);

        return prHead;
    }
    public PdfPTable addColorLineEmptyTableGray(int lines) {
        int i;

        // Create Table into Document with
        PdfPTable myTable = new PdfPTable(lines);
        for (i = 0; i < lines; i++) {

            myTable.setWidthPercentage(100.0f);
            PdfPCell myCell = new PdfPCell(new Paragraph(""));
            myCell.setBorder(Rectangle.BOTTOM);
            myCell.setBorderColor(new BaseColor(95, 96, 108));

            // Add Cell into Table
            myTable.addCell(myCell);
        }
        return myTable;
    }

}
//ADDING SPACING BETWEEN LINES: paragraph.setLeading(fixed, multiplied);
//table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
