package com.session2.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class để in dữ liệu dạng bảng với các cột được căn chỉnh đẹp mắt
 */
public class TablePrinter {
    private List<String> headers;
    private List<List<String>> rows;
    private List<Integer> columnWidths;
    private List<Alignment> alignments;

    public enum Alignment {
        LEFT, CENTER, RIGHT
    }

    public TablePrinter() {
        this.headers = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.columnWidths = new ArrayList<>();
        this.alignments = new ArrayList<>();
    }

    /**
     * Thiết lập headers cho bảng
     */
    public TablePrinter setHeaders(String... headers) {
        this.headers = new ArrayList<>();
        for (String header : headers) {
            this.headers.add(header);
            this.columnWidths.add(header.length());
            this.alignments.add(Alignment.LEFT); // Default alignment
        }
        return this;
    }

    /**
     * Thiết lập căn chỉnh cho các cột
     */
    public TablePrinter setAlignments(Alignment... alignments) {
        this.alignments = new ArrayList<>();
        for (Alignment alignment : alignments) {
            this.alignments.add(alignment);
        }
        return this;
    }

    /**
     * Thêm một dòng dữ liệu
     */
    public TablePrinter addRow(Object... values) {
        List<String> row = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            String value = values[i] == null ? "" : values[i].toString();
            row.add(value);

            // Cập nhật độ rộng cột nếu cần
            if (i < columnWidths.size()) {
                columnWidths.set(i, Math.max(columnWidths.get(i), value.length()));
            } else {
                columnWidths.add(value.length());
            }
        }
        rows.add(row);
        return this;
    }

    /**
     * In bảng ra console
     */
    public void print() {
        if (headers.isEmpty()) {
            return;
        }

        // In đường viền trên
        printBorder("top");

        // In header
        printRow(headers, true);

        // In đường viền giữa
        printBorder("middle");

        // In các dòng dữ liệu
        for (List<String> row : rows) {
            printRow(row, false);
        }

        // In đường viền dưới
        printBorder("bottom");

        System.out.println();
    }

    /**
     * In một dòng
     */
    private void printRow(List<String> row, boolean isHeader) {
        System.out.print(ConsoleColors.CYAN_BOLD + "│ " + ConsoleColors.RESET);

        for (int i = 0; i < row.size(); i++) {
            String value = row.get(i);
            int width = i < columnWidths.size() ? columnWidths.get(i) : value.length();
            Alignment alignment = i < alignments.size() ? alignments.get(i) : Alignment.LEFT;

            String formatted = formatCell(value, width, alignment);

            if (isHeader) {
                System.out.print(ConsoleColors.YELLOW_BOLD + formatted + ConsoleColors.RESET);
            } else {
                System.out.print(ConsoleColors.WHITE + formatted + ConsoleColors.RESET);
            }

            if (i < row.size() - 1) {
                System.out.print(ConsoleColors.CYAN_BOLD + " │ " + ConsoleColors.RESET);
            }
        }

        System.out.println(ConsoleColors.CYAN_BOLD + " │" + ConsoleColors.RESET);
    }

    /**
     * Format một ô theo độ rộng và căn chỉnh
     */
    private String formatCell(String value, int width, Alignment alignment) {
        if (value.length() > width) {
            return value.substring(0, width);
        }

        int padding = width - value.length();

        switch (alignment) {
            case LEFT:
                return value + " ".repeat(padding);
            case RIGHT:
                return " ".repeat(padding) + value;
            case CENTER:
                int leftPad = padding / 2;
                int rightPad = padding - leftPad;
                return " ".repeat(leftPad) + value + " ".repeat(rightPad);
            default:
                return value;
        }
    }

    /**
     * In đường viền
     */
    private void printBorder(String position) {
        String left, middle, right, horizontal;

        switch (position) {
            case "top":
                left = "┌";
                middle = "┬";
                right = "┐";
                horizontal = "─";
                break;
            case "middle":
                left = "├";
                middle = "┼";
                right = "┤";
                horizontal = "─";
                break;
            case "bottom":
                left = "└";
                middle = "┴";
                right = "┘";
                horizontal = "─";
                break;
            default:
                return;
        }

        System.out.print(ConsoleColors.CYAN_BOLD + left + horizontal + ConsoleColors.RESET);

        for (int i = 0; i < columnWidths.size(); i++) {
            System.out.print(ConsoleColors.CYAN_BOLD + horizontal.repeat(columnWidths.get(i)) + ConsoleColors.RESET);

            if (i < columnWidths.size() - 1) {
                System.out.print(ConsoleColors.CYAN_BOLD + horizontal + middle + horizontal + ConsoleColors.RESET);
            }
        }

        System.out.println(ConsoleColors.CYAN_BOLD + horizontal + right + ConsoleColors.RESET);
    }

    /**
     * Xóa tất cả dữ liệu
     */
    public void clear() {
        headers.clear();
        rows.clear();
        columnWidths.clear();
        alignments.clear();
    }
}
