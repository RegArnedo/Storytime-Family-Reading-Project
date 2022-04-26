<template>
  <transition name="modal-fade">
    <div class="modal-overlay" @click="$emit('close-modal')">
      <div class="modal" @click.stop>
        <div class="title">
          <h1 id="modal-h1"> Reading details for <span id='modal-span'>{{userDetail_name}}</span></h1>
        </div>
        <table class="modal-parent">
            <label for="Children"></label>
              <tr class='modal-table-header'>
                <th class='left-end-top' id='header'>Title</th>
                <th class='middle' id='header'>Author</th>
                <th class='middle' id='header'>Genre</th>
                <th class='right-end-top' id='header'>Minutes</th>
                <th class='right-end-top' id='header'>Times Read</th>
                <th class='right-end-top' id='header'>Date</th>
              </tr>
              <tr v-for="booklog in childData" :key="booklog.index">  
                <td class='left-end'>{{booklog.title}}</td> 
                <td class='middle'>{{booklog.author}}</td>
                <td class='middle'> {{booklog.genre}}</td>
                <td class='right-end'>{{booklog.minutes_per_book}}</td>
                <td class='right-end'>{{booklog.times_read_total}}</td>
                <td class='right-end'>{{booklog.last_read}}</td>
              </tr>
            </table> 
      </div>
      <div class="close" @click="$emit('close-modal')">
        <img class="close-img" src="src\assets\booklog.png" alt="" />
      </div>
    </div>
  </transition>
  
</template>

<script>
import BookService from "@/services/BookService";

  export default {
    name: 'child-modal-userID', 
    props: ['userDetail_id', 'userDetail_name'],
  data() {
    return {

      childData: [] ,

    }
  },
    computed: {
    currentUserId: function(){
      return this.$store.state.user.id
    }, 
  },
  created() {
    BookService.getUserDetail(this.userDetail_id).then(response => {
      this.childData = response.data
    })
  }
  };
    

</script>

<style scoped>
#modal-h1{
  font-size: 50px;
}
#modal-span{
  font-size: 40px;
}
.modal-overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: center;
  align-content: center;
  background-color: #000000da;
}
.modal {
  text-align: left;
  background-color: rgb(217, 245, 221, 0.9);
  height: 770px;
  width: 1200px;
  margin-top: 1%;
  padding: 0px 0;
  border-radius: 5px;
  overflow: scroll;
   overflow-x: hidden;
}
.close {
  margin: 10% 0 0 16px;
  cursor: pointer;
}
.close-img {
  width: 25px;
}
.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}
.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.5s ease;
}
.modal-parent {
  padding-top: 20px;
  float: left;
  width: 100%;
  justify-content: left;
}
.modal-parent td, .parent th {
  border: 1px solid #f3f7f4;
  padding: 8px; 
}
.modal-parent tr:nth-child(even){background-color: #ffffff;}

.modal-parent tr:hover {background-color: rgba(18, 108, 124, 0.466)}

.modal-parent th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: rgb(18, 108, 124, 0.9);
  color: #f2f7f3;
}
#header{
  padding-left: 9px;
}
</style>