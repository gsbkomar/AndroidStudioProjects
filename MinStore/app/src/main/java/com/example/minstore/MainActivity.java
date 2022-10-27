package com.example.minstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minstore.adapter.CategoryAdapter;
import com.example.minstore.adapter.CourseAdapter;
import com.example.minstore.model.Category;
import com.example.minstore.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCourseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Игры"));
        categoryList.add(new Category(2, "Сайты"));
        categoryList.add(new Category(3, "Языки"));
        categoryList.add(new Category(4, "Прочее"));

        setCategoryRecycler(categoryList);

        courseList.add(new Course(1, "java", "Профессия Java\nразработчик", "1 января", "начальный", "#424345", "Test", 3));
        courseList.add(new Course(2, "python", "Профессия Java\nразработчик", "10 января", "начальный", "#9FA52D", "Test", 3));
        courseList.add(new Course(3, "unity", "Профессия Unity\nразработчик", "5 января", "начальный", "#651730", "Test", 1));
        courseList.add(new Course(4, "front_end", "Профессия Front-end\nразработчик", "6 января", "начальный", "#B04935", "Test", 2));
        courseList.add(new Course(5, "back_end", "Профессия Back-end\nразработчик", "8 января", "начальный", "#2D55A5", "Test", 2));
        courseList.add(new Course(6, "full_stack", "Профессия Full-stack\nразработчик", "10 января", "средний", "#FFC007", "Test", 2));

        fullCourseList.addAll(courseList);
        setCourseRecycler(courseList);

    }
    public void openShoppingPage(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public static void showCoursesByCategory(int category) {

        courseList.clear();
        courseList.addAll(fullCourseList);
        List<Course> filterCourse = new ArrayList<>();

        for (Course c : courseList) {
            if (c.getCategory() == category)
                filterCourse.add(c);
        }

        courseList.clear();
        courseList.addAll(filterCourse);

        courseAdapter.notifyDataSetChanged();


    }

}