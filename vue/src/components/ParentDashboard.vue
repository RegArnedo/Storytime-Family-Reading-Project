<template> 
    <div class="parent-dashboard">
      <div class="title">
        <h1>Parent Dashboard<span>Keep track of your kid's reading. Build habits for life.</span></h1>
      </div>
      <div class="box"> 
        <div class="left-side">
          <div class="grid-item-1">
            <table class="parent">
            <label for="Children"></label>
              <tr>
                <th class='left-end-top'>Child</th>
                <th class='middle'>Books Completed</th>
                <th class='middle'>Minutes Read</th>
                <th class='right-end-top'>Current Book</th>
              </tr>
              <tr v-for="user in familyUsers" :key="user.id" @click="clickSelectedUser(user.user_id, user.first_name)">
                <td class='left-end'>{{user.first_name}}</td>
                <td class='middle'>{{user.books_read}}</td>
                <td class='middle'> {{user.total_minutes_read}}</td>
                <td class='right-end'>{{user.current_book}}</td>
              </tr>
            </table> 
          </div>
          <img src="@/assets/Dashboard.png" class="grid-item-2"/>
        </div>
      <div class="right-side">
        <reading-log v-bind:familyUsers="familyUsersAll" />
        <add-book v-bind:familyUsers="familyUsersAll"/>
        <registration-form-child /> 
        <user-modal v-if="showModal"  v-bind:userDetail_id="selectedUserDetailId" v-bind:userDetail_name="selectedUserDetailName" @close-modal="showModal = false"/>
      </div> 
    </div>    
  </div>
</template>

<script>
import AuthService from "@/services/AuthService";
import AddBook from './AddBook.vue';
import RegistrationFormChild from './RegistrationFormChild.vue'
import ReadingLog from './ReadingLog.vue'
import UserModal from './UserModal.vue'

export default {
  components: { AddBook, RegistrationFormChild, ReadingLog, UserModal  },
  name: 'parent-dashboard-userID',  
 
  data() {
    return {

      familyUsers: [],
      userData: [],
      familyUsersAll: [] ,
      showModal: false,
      clickedUserDetail_id: 2,
      selectedUserDetailId: '',
      selectedUserDetailName: '',
    }
  },
    computed: {
    currentUserId: function(){
      return this.$store.state.user.id
    }, 
  },
  methods: {
    goToRegisterChild(){
      this.$router.push({name: 'register-child'})
    },
    clickSelectedUser(id, name){
       this.selectedUserDetailId = id
       this.selectedUserDetailName = name
       this.showModal = true
    }
  },

created() {
    AuthService.getFamilyByUserId(this.currentUserId).then(response => {
          this.familyUsersAll = response.data;
      })
    AuthService.getDashboardInfo(this.$store.state.user.family_id).then(response => {
      this.familyUsers = response.data
    })
  }
}

</script>

<style>
.box{
  display: flex;
  justify-content: space-around;
}
.parent {
  padding-top: 40px;
  float: left;
  width: 100%;
  justify-content: center;
}
.parent td, .parent th {
  border: 1px solid hsla(0, 0%, 100%, 0.952);
  padding: 8px;  
  background-color: rgba(249, 252, 250, 0.493)
}
.parent tr:nth-child(even){
  background-color: rgb(208,227,214, 0.7) ;}

.parent tr:hover {background-color: #e5f7e8}

.parent th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: rgba(17, 99, 114, 0.719);
  color: #f2f7f3;
}
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