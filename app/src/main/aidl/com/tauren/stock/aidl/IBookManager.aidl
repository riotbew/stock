// IBookManager.aidl
package com.tauren.stock.aidl;

// Declare any non-default types here with import statements
import com.tauren.stock.aidl.Book;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
}