package com.session2;

import com.session2.presentation.MainMenu;

/**
 * Ứng dụng Quản lý Khóa học và Sinh viên
 */
public class App 
{
    public static void main( String[] args )
    {
        MainMenu mainMenu = new MainMenu();
        mainMenu.display();
        mainMenu.close();
    }
}
