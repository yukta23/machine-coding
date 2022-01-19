package com.library.models;

import java.util.HashSet;

public class Rack {
	// hash set so that bookIds per rack don't repeat
		private HashSet<Integer> bookIds;
		private HashSet<Integer> bookCopyIds;
		public Rack() {
			this.bookCopyIds = new HashSet<>();
			this.bookIds = new HashSet<>();
		}
		public HashSet<Integer> getBookIds() {
			return bookIds;
		}
		public void setBookIds(HashSet<Integer> bookIds) {
			this.bookIds = bookIds;
		}
		public HashSet<Integer> getBookCopyIds() {
			return bookCopyIds;
		}
		public void setBookCopyIds(HashSet<Integer> bookCopyIds) {
			this.bookCopyIds = bookCopyIds;
		}
}
