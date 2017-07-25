package com.abhimanyusharma.resumetemplatesapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PDFActivity extends AppCompatActivity {

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

           u_name = "ResumeInc.",
           u_email = "abhimanyusharma96.edu@gmail.com",
           u_number = "+91-9999999999",
           u_address, u_year_branch = "4th Year, Computer Science Engineering, Galgotias College of Engineering",
           u_portfolio = "http://abhimanyu96edu.github.io",
           u_linkedin = "www.github.io/abhimanyu96edu",

           u_pe_title = "Resume Inc. App",
           u_pe_description = "It is an Offline Super Duper, Fast, Easy to Use, Instant Resume Builder",
           u_pe_link = "http://www.github.com/abhimanyu96edu/resume_inc.",
           u_pe_end_date = "JULY-2017",

           u_ts_description = "\u2202 Android Development",

           u_w_designation = "CTO, Worked at ",
           u_w_organization = "Resume Inc.",
           u_w_description = "CTO and Founder of Resume Inc.",
           u_w_start_date = "JUN-2017",
           u_w_end_date = "JULY-2017",
           u_radioGroup = "Worked";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        tv = (TextView) findViewById(R.id.tv);

        requestPermissions();

// Create Directory in External Storage
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

// Destination Folder and File name
        String FILE = Environment.getExternalStorageDirectory().toString()
                + "/PDF/Default_Resume.pdf";

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

// Open Document for Writting into document
        document.open();

// User Define Method
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

        //startActivity(new Intent(getApplicationContext(), Resume.class));
        //finish();

    }

    // Set PDF document Properties
    public void addMetaData(Document document) {
        document.addTitle("RESUME");
        document.addSubject("Resume");
        document.addKeywords("Personal, Education, Skills, Work");
        document.addAuthor("Abhimanyu Sharma");
        document.addCreator("Abhimanyu Sharma");
    }

    public void addTitle(Document document) throws DocumentException {

        //USER NAME AT TOP
        // Font Style for Document
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD , BaseColor.BLACK);
        // Start New Paragraph
        Paragraph prHead = new Paragraph();
        // Set Font in this Paragraph
        prHead.setFont(titleFont);
        // Add item into Paragraph
        prHead.add(u_name + "\n");

        prHead.setAlignment(Element.ALIGN_CENTER);
        // Add all above details into Document
        document.add(prHead);

        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));

    }

    private void addContent(Document document) throws DocumentException, IOException {

        Font headingFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE, BaseColor.GRAY);
        Font specialFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font normalBoldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.DARK_GRAY);
        Font smallBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.DARK_GRAY);

// Now Start another New Paragraph
        //SPECIAL INTRO
        Paragraph prSpecial = new Paragraph();
        prSpecial.setFont(specialFont);

        //prSpecial.add("\n\u25D3 \u2023 ‣ 3rd year, Computer Science Engineering Student, Galgotias College of Engineering and Technology");
        //prSpecial.add("\n\u25D3 \u2023 ‣ abhimanyusharma96.edu@gmail.com");
        //prSpecial.add("\n\u25D3 \u2022 • +91-999999999");
        //prSpecial.add("\n\u25D3 \u25E6 ◦ 204, galogotias college of engineering, in campus, boys hostel, greater noida 201306");//"Address:" +
        //prSpecial.add("\n\u25D3 \u2219 ∙ abhimanyusharma96edu.github.com.io");
        //prSpecial.add("\n\u25D3 \u2043 ⁃ abhimanyusharma96edu.github.com.io");
        //prSpecial.add("\u2022  " + u_year_branch);
        //prSpecial.add("\n\u2022  " + u_email);
        prSpecial.add("\n-  " + u_year_branch);
        prSpecial.add("\n-  " + u_email);
        prSpecial.add("\n-  " + "+91-" + u_number);
        prSpecial.add("\n-  " + u_address);
        prSpecial.add("\n-  " + u_portfolio);
        prSpecial.add("\n-  " + u_linkedin);

        prSpecial.setAlignment(Element.ALIGN_LEFT);

        //document.add(prSpecial);
        //document.add(addEmptyLine(1));
        //document.add(addColorLineEmptyTable(1));

        //PROFILE IMAGE: http://www.concretepage.com/itext/add-image-in-pdf-using-itext-in-java
        try {
            InputStream inputStream = getAssets().open("logo.png");
            Bitmap bmp = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            //picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image logoImage = Image.getInstance(stream.toByteArray());
            logoImage.scalePercent(60);
            logoImage.scaleAbsolute(80, 80);
            //document.add(logoImage);

//ADDING IMAGE AND TEXT TO TABLE
            PdfPTable tablez = new PdfPTable(3);
            tablez.setWidthPercentage(98.0f);
            tablez.setWidths(new int[]{5, 3, 2});

            PdfPCell cellOne = new PdfPCell(prSpecial);
            PdfPCell cellTwo = new PdfPCell(new Paragraph());
            PdfPCell cellThree = new PdfPCell(logoImage);

            cellOne.setBorder(Rectangle.NO_BORDER);
            cellTwo.setBorder(Rectangle.NO_BORDER);
            cellThree.setBorder(Rectangle.NO_BORDER);
            //cellOne.setBackgroundColor(new Color(255,255,45));
            //cellTwo.setBorder(Rectangle.BOX);

            tablez.addCell(cellOne);
            tablez.addCell(cellTwo);
            tablez.addCell(cellThree);
            document.add(tablez);

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Image Not Added", Toast.LENGTH_SHORT).show();

            Paragraph prException = new Paragraph();
            prException.setFont(specialFont);
            prException.add("\n-  " + u_year_branch);
            prException.add("\n-  " + u_email);
            prException.add("\n-  " + "+91-" + u_number);
            prException.add("\n-  " + u_address);
            prException.add("\n-  " + u_portfolio);
            prException.add("\n-  " + u_linkedin);

            prException.setAlignment(Element.ALIGN_LEFT);

            document.add(prException);
        }
        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));

        //CAREER OBJECTIVE

        document.add(addHeadTitle("Career Objective:"));

        Paragraph prCareer = new Paragraph();
        prCareer.setFont(normalFont);

        prCareer.add(u_co_description + "\n");

        prCareer.setAlignment(Element.ALIGN_LEFT);

        document.add(prCareer);

        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));

    //EDUCATION DETAILS

        document.add(addHeadTitle("Education Qualification:"));
        document.add(addEmptyLine(1));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(98.0f);

        //Add Column Headings
        Paragraph columnText_0 = new Paragraph();
        columnText_0.setFont(normalBoldFont);
        columnText_0.setAlignment(Element.ALIGN_CENTER);
        columnText_0.add("Course");
        Paragraph columnText_1 = new Paragraph();
        columnText_1.setFont(normalBoldFont);
        columnText_1.setAlignment(Element.ALIGN_CENTER);
        columnText_1.add("University/Board");
        Paragraph columnText_2 = new Paragraph();
        columnText_2.setFont(normalBoldFont);
        columnText_2.setAlignment(Element.ALIGN_CENTER);
        columnText_2.add("Percentage");
        Paragraph columnText_3 = new Paragraph();
        columnText_3.setFont(normalBoldFont);
        columnText_3.setAlignment(Element.ALIGN_CENTER);
        columnText_3.add("Year");

        PdfPCell column_Title_0 = new PdfPCell(columnText_0);
        PdfPCell column_Title_1 = new PdfPCell(columnText_1);
        PdfPCell column_Title_2 = new PdfPCell(columnText_2);
        PdfPCell column_Title_3 = new PdfPCell(columnText_3);

        columnText_0.setAlignment(Element.ALIGN_CENTER);
        columnText_1.setAlignment(Element.ALIGN_CENTER);
        columnText_2.setAlignment(Element.ALIGN_CENTER);
        columnText_3.setAlignment(Element.ALIGN_CENTER);

        //Add table Rows
        table.addCell(column_Title_0);
        table.addCell(column_Title_1);
        table.addCell(column_Title_2);
        table.addCell(column_Title_3);
        table.setHeaderRows(1);


        //List lt1 = new List(List.ORDERED);
        //lt1.setAutoindent(false);
        //lt4.setSymbolIndent(25);

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
        table.addCell(u_e_college);
        table.addCell(u_e_course);
        table.addCell(u_e_marks);
        table.addCell(u_e_end_date);

        document.add(table);

        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));

    //PROJECT EXPERIENCE

        document.add(addHeadTitle("Project Experience:"));

        //java.util.List<ProjectExperienceData> list2 = ProjectExperienceData.getProjectExperienceValue(u_userEmail);

        //for (int i = 0; i < list2.size(); i++) {
        //    ProjectExperienceData ped = list2.get(i);

            Paragraph prProject = new Paragraph();
            prProject.setFont(normalFont);
            //prWork.add(String.valueOf(i + 1) + ". " + ped.getPe_title() + "  \u2014  " + ped.getPe_link() + "     " + "(" + ped.getPe_end_date() + ")");
            prProject.add("\u2202 " + u_pe_title + "  \u2014  " + u_pe_link + "     " + "(" + u_pe_end_date + ")");
            prProject.setAlignment(Element.ALIGN_LEFT);
            document.add(prProject);

            Paragraph pe1 = new Paragraph();
            pe1.setFont(smallBoldFont);
            //pe2.add("     " + ped.getPe_description());
            pe1.add("     " + u_pe_description);
            pe1.setAlignment(Element.ALIGN_LEFT);
            document.add(pe1);
//        }

        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));

        //WORK EXPERIENCE

        document.add(addHeadTitle("Work Experience:"));

        /*java.util.List<WorkExperienceData> list3 = WorkExperienceData.getWorkExperienceValue(u_userEmail);

        for (int i = 0; i < list3.size(); i++) {
            WorkExperienceData wed = list3.get(i);

            String status = "";
            if (wed.getW_radioGroup() == "Current Employee")
                status = "Working";
            else
                status = "Worked";
*/
            Paragraph prWork = new Paragraph();
            prWork.setFont(normalFont);
            //prWork.add(String.valueOf(i + 1) + ". " + wed.getW_designation() + ", " + status + " at " + wed.getW_organization());
            prWork.add("\u2202" + u_w_designation +  u_w_organization);
            prWork.setAlignment(Element.ALIGN_LEFT);
            document.add(prWork);

            Paragraph pe2 = new Paragraph();
            pe2.setFont(smallBoldFont);
            pe2.add("    " + u_w_start_date + " \u2014 " + u_w_end_date);
            pe2.setAlignment(Element.ALIGN_LEFT);
            document.add(pe2);

            Paragraph pe3 = new Paragraph();
            pe3.setFont(smallBoldFont);
            pe3.add("    " + u_w_description);
            pe3.setAlignment(Element.ALIGN_LEFT);
            document.add(pe3);
//        }

        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));

        //TECHNICAL SKILLS

        document.add(addHeadTitle("Technical Skills:"));

        //List lt4 = new List(List.ORDERED);
        //lt4.setAutoindent(false);
        //lt4.setSymbolIndent(15);

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

            document.add(prSkills);
//        }

        //document.add(lt4);


        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));

        //ACHIEVEMENTS

        document.add(addHeadTitle("Achievements:"));


        //List lt5 = new List(List.ORDERED);
        //lt5.setAutoindent(false);
        //lt5.setSymbolIndent(25);
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
            document.add(prAchievements);
        //}

        //document.add(lt5);

        document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTable(1));
        document.add(addEmptyLine(3));

        //SIGNATURE

        Paragraph pe4 = new Paragraph();
        pe4.setFont(smallBoldFont);
        pe4.add("SIGNATURE" + "\n");
        pe4.add("(" + u_name + ")");
        pe4.setAlignment(Element.ALIGN_LEFT);
        document.add(pe4);

        document.add(addEmptyLine(1));

// Create new Page in PDF
        document.newPage();

    }

    private Paragraph addHeadTitle(String s) {

        Font headingFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE, BaseColor.DARK_GRAY);
        Paragraph prHead = new Paragraph();
        prHead.setFont(headingFont);
        prHead.add(s);

        prHead.setAlignment(Element.ALIGN_LEFT);

        return prHead;
    }

//Empty Line Generator

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

            // 100.0f mean width of table is same as Document size
            myTable.setWidthPercentage(100.0f);

            // Create New Cell into Table
            PdfPCell myCell = new PdfPCell(new Paragraph(""));
            myCell.setBorder(Rectangle.BOTTOM);
            myCell.setBorderColor(BaseColor.GRAY);

            // Add Cell into Table
            myTable.addCell(myCell);
        }
        return myTable;
    }

    public void requestPermissions() {
        Log.d("RESULT", "----------------------------------requestPermissions: ");
        ActivityCompat.requestPermissions(PDFActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), ChooseTemplateActivity.class));
        finish();
    }
}

//______________________________________________________
// BEST EXAMPLE:
// http://www.concretepage.com/itext/create-pdf-with-text-list-table-in-java-using-itext
// http://www.concretepage.com/search/?cx=003115115016125228104%3AWMX-1778465153&cof=FORID%3A10&ie=UTF-8&q=iText&siteurl=www.concretepage.com%2Fitext%2Fcreate-pdf-with-text-list-table-in-java-using-itext&ref=&ss=877j212099j6
// GitHub: Telephone_Directory_Generator
//______________________________________________________

/*
u_userEmail, u_userPassword,
u_achievement,
u_profileImage,
u_co_description,
u_e_college, u_e_course, u_e_marks, u_e_start_date, u_e_end_date,
u_name, u_email, u_number, u_address, u_year_branch, u_portfolio, u_linkedin,
u_pe_title, u_pe_description, u_pe_link, u_pe_end_date,
u_ts_description,
u_w_designation, u_w_organization, u_w_description, u_w_start_date, u_w_end_date, u_w_radioGroup
 */
/*
ADDING IMAGE FROM DRAWABLE FOLDER
__________________________________
try {

  document.open();

  Drawable d = getResources().getDrawable(R.drawable.myImage);

  BitmapDrawable bitDw = ((BitmapDrawable) d);

  Bitmap bmp = bitDw.getBitmap();

  ByteArrayOutputStream stream = new ByteArrayOutputStream();

  bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

  Image image = Image.getInstance(stream.toByteArray());

  document.add(image);

  document.close();

} catch (Exception e) {
  e.printStackTrace();
}
 */

