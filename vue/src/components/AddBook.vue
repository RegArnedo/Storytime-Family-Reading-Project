<template>
    
<div>
  <form class="form-add-book">
    <h1 class="form-header">Add A Book To Family Collection</h1>
    <div class="form-body">
            <!-- Title and Author -->
            <div class="horizontal-group">
                <div class="form-group left">
                    <label for="title" class="label-title">Book Title</label>
                    <input
                        type="text"
                        id="title"
                        class="form-input"
                        placeholder=""
                        v-model="book.title"
                        required
                        autofocus
                    />
                    </div>
                <div class="form-group right">
                    <label for="author" class="label-title">Author</label>
                    <input
                        type="text"
                        id="author"
                        class="form-input"
                        placeholder=""
                        v-model="book.author"
                        autofocus
                    />
                    </div>
            </div>
            <!-- ISBN and Difficulty-->
            <div class="horizontal-group">
                    <div class="form-group left">
                        <label for="email" class="label-title">ISBN</label>
                        <input
                            type="text"
                            id="isbn"
                            class="form-input"
                            placeholder=""
                            v-model="book.isbn"
                            autofocus
                        />
                    </div>
                    <div class="form-group right">
                      <div class="split-form-one-group-right">
                      <label for="difficulty" id="genre" class="label-title">Genre</label>
                    <select
                        class="drop-down"
                        name="genre"
                        id="genre"
                        v-model="book.genre"
                        autofocus
                    >
                      <option disabled hidden value="">Select</option>
                      <option selected value="Early Reader">Early Reader</option>
                      <option value="Classic">Classic</option>
                      <option value="Young Adult">Young Adult</option>
                      <option value="Reference">Reference</option>
                      <option value="Non-Fiction">Non-Fiction</option>
                    </select>
                    </div>
                    <div class="split-form-two-group-right">
                    <label for="difficulty" id="difficulty" class="label-title">Difficulty</label>
                    <select
                        class="drop-down"
                        name="difficulty"
                        id="difficulty"
                        v-model="book.difficulty"
                        autofocus
                    >
                      <option disabled hidden value="">Select</option>
                      <option selected value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                    </select>
                    </div>
                    </div>
                </div>
            </div>
              <div class="form-footer">
                <button class="btn" type="submit" v-on:click="addBook">
                Add Book
                </button>  
                </div> 
  </form>
</div>
</template>

<script>

import BookService from '@/services/BookService.js';

export default {
  name: 'add-book',
  props: ['familyUsers'],  
  data() {
    return {
      book: {
        title: "",
        author: "",
        isbn: "",
        difficulty: "",
        genre: "",
      },
      assignToUser: 1,
      isbnErrors: false,
      isbnErrorMsg: 'ISBN needs to be a 13 digit number.',
      
    };
  },
  methods: {
    
    addBook() {
      BookService.addBook(this.assignToUser, this.book)
      .then(response => {
        if (response.status === 201) {              
          this.book = {
            title: '',
            author: '',
            isbn: '',
            difficulty: '',
            genre: '',       
          },
          alert('AddBook successful!  Hurray for our side!')
        }
      })
    }
  }
}


</script>

<style>
/*---------------------------------------*/
/* Buttons */
/*---------------------------------------*/
.btn-body {
  display: inline-block;
  padding: 10px 10px;
  background-color: #1ca3dc;
  font-size: 17px;
  border: none;
  border-radius: 5px;
  color: #bcf5e7;
  cursor: pointer;
}
.btn-body:hover {
  background-color: #157788;
  color: white;
}
</style>