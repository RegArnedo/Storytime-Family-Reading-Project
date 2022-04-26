<template>
  <div class="add-book">
    <div class="title">
    <h1>Reading History<span>Welcome to your library</span></h1>
    </div>
    <table class="history">
      <thead class="table-header">
        <tr class="header-row">
          <th class='left-end-top'>Date</th>
          <th class='middle'>First Name</th>
          <th class='middle'>Title</th>
          <th class='middle'>Author</th>
          <th class='middle'>Genre</th>
          <th class='middle'>Format</th>
          <th class='middle'>Difficulty</th>
          <th class='middle'>Minutes Reading</th>  
          <th class='middle'>Book Finished</th>                     
          <th class='right-end-top'>Review</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="log in userLogs" v-bind:key="log.index" class="table-row">
          <td class='left-end'>{{ log.date_logged }}</td>
          <td class='middle'>{{ log.first_name }}</td>
          <td class='middle'>{{ log.title }}</td>
          <td class='middle'>{{ log.author }}</td>
          <td class='middle'>{{ log.genre }}</td>
          <td class='middle'>{{ log.format }}</td>
          <td class='middle'>{{ log.difficulty }}</td>
          <td class='middle'>{{ log.minutes_read }}</td>
          <td class='middle'>{{ log.finished_book }}</td>          
          <td class='right-end'>{{ log.review }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import BookService from "@/services/BookService.js";

export default {
  name: "History",
  data() {
    return {
      currentUserId: this.$store.state.user.id,
      userLogs: [],
      books: [],
    };
  },
  components: {},
  created() {
    BookService.getReadingLog(this.currentUserId).then((response) => {
      this.userLogs = response.data;
    });
  },
};
</script>

<style>
.history {
  margin-left: auto;
  margin-right: auto;
  width: 70%;
  justify-content: center;
  font-weight: 100;
}
.history td, .history th {
  border: 1px solid #bbf2fc;
  padding: 10px;
  
}
.history tr:nth-child(even){background-color: #f2f2f2;}

.history th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #126c7c;
  color: white;
}
.history th:hover {background-color: #ddd;}

.left-end{
border-radius: 8px 3px 3px 8px;
}
.middle {
border-radius: 3px 3px 3px 3px;
}
.right-end{
border-radius: 3px 8px 8px 3px;
}
.right-end-top{
border-radius: 3px 8px 5px 3px;
}
.left-end-top {
border-radius: 8px 3px 3px 5px;
}
</style>