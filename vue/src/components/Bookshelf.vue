<template>
<div>
    <div class='buttons'>
    <div row class="mb-3">
      <button class="btn"  @click="sortBy('title')">By Title</button>
      <button class="btn"  @click="sortBy('author')">By Author</button>
      <button class="btn"  @click="sortBy('genre')">By Genre</button>
      <button class="btn"  @click="sortBy('difficulty')">By Difficulty</button>
    </div >
    </div>
  <div class="book-shelf">
    <book-card v-for="book in this.books" v-bind:key="book.id" v-bind:book="book"/>  
  </div>
</div>
</template>

<script>
import BookService from "@/services/BookService.js";
import BookCard from './BookCard.vue';


export default {
  name: "Bookshelf",
  data() {
    return {
      currentFamilyId: this.$store.state.user.family_id,
      books: [],
    };
  },
  components: {
    BookCard
    
  },

    methods: {
    sortBy(property){
      this.books.sort((a,b) => a[property].toString().toLowerCase() < b[property].toString().toLowerCase() ? -1 : 1);
    }
  },

  created() {
    BookService.getBooks(this.currentFamilyId).then((response) => {
      this.books = response.data;
      this.sortBy('title')
    });
    
  },
};
</script>

<style>
.buttons{
  display:flex;
  justify-content: center;
  padding-top: 40px;
  padding-bottom: 55px;
}
.book-shelf {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}

</style>