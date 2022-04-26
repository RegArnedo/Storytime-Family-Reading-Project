<template>
  <div class="child-dashboard">
    <div class="title">
      <h1>
        {{ this.childDashboardInfo.first_name }}'s Dashboard<span
          >What adventures are we going on today?</span
        >
      </h1>
    </div>
    <div class="box">
      <div class="left-side">
        <div class="grid-item-1">
          <table class="parent">
            <label for="Children"></label>
            <tr>
              <th class="left-end-top">Name</th>
              <th class="middle">Books Completed</th>
              <th class="middle">Minutes Read</th>
              <th class="right-end-top">Current Book</th>
            </tr>
            <tr>
              <td class="left-end">{{ this.childDashboardInfo.first_name }}</td>
              <td class="middle">{{ this.childDashboardInfo.books_read }}</td>
              <td class="middle">{{ this.childDashboardInfo.total_minutes_read }}</td>
              <td class="right-end">still need</td>
            </tr>
          </table>
        </div>
        <img src="@/assets/Dashboard.png" class="grid-item-2" />
      </div>
      <div class="right-side">
        <reading-log class="reading-log" v-bind:familyUsers="familyUsersAll" />
        <add-book v-bind:familyUsers="familyUsersAll" />
      </div>
    </div>
  </div>
</template>

<script>
import AddBook from "./AddBook.vue";
import ReadingLog from "./ReadingLog.vue";
import AuthService from "@/services/AuthService";

export default {
 components: { AddBook, ReadingLog },
  data() {
    return {
      familyUsersAll: [],
      currentUser: this.$store.state.user,
      test: "test",
      dashboardInfo: [],
      childDashboardInfo: {}
    };
  },
  computed: {
    currentUserId: function () {
      return this.$store.state.user.id;
    },

  },
  created() {
    AuthService.getFamilyByUserId(this.currentUserId).then((response) => {
      this.familyUsersAll = response.data;
    });
    AuthService.getDashboardInfo(this.$store.state.user.family_id).then(
      (response) => {
        this.dashboardInfo = response.data;
        this.childDashboardInfo = this.dashboardInfo.find(element => element.user_id === this.currentUserId)
      }
    );
  },
};
</script>

<style>


</style>