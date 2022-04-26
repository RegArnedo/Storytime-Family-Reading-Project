<template>
<div class="add-book">
  <div class="title">
    <h1>BookShelf<span>Let's go on a reading adventure!</span></h1>
    </div>
  <div class="book-container">
    <bookshelf />
  </div>
</div>
</template>

<script>
import Bookshelf from '../components/Bookshelf.vue'

export default {
  components: { Bookshelf },
    name: 'bookshelf-view',
}
</script>

<style>
.book-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>