<template>
  <div class="reading-log">
    <div>
      <form class="form-reading-log">
        <h1 class="form-header">Log Reading</h1>
        <div class="form-body">
          <!-- Reader and Book -->
          <div class="horizontal-group">
            <div class="form-group-wide left">
              <div class="split-form-one-group-left">
                <!-- Thought we could implement this somehow to swap between parent or child presentation of form but it disappeared from view on page now and IDK why -->
                <div v-if="isParent">
                  <label class="label-title" for="readers">Reader</label>
                  <select
                    class="drop-down"
                    name="readers"
                    id="readers"
                    v-model="userBook.user_id"
                    required
                    autofocus
                  >
                    <option class='links' disabled hidden value="">Pick a Reader</option>
                    <option class='links'
                      v-for="user in familyUsers"
                      v-bind:key="user.index"
                      :value="user.id"
                    >
                      {{ user.first_name }}
                    </option>
                    </select
                  ><br />
                </div>
                <div v-else>
                  <p class="label-kid" for="readers">
                    {{ currentUser.first_name }}
                  </p>
                </div>
              </div>
              <div class="split-form-two-group-left">
                <label for="assignedBooks" class="label-title">Book</label>
                <select
                  class="drop-down"
                  name="book"
                  id="assignedbooks"
                  v-model="userBook.book_id"
                  autofocus>
                  <option class='links' disabled hidden value="">Pick a Book</option>
                  <option class='links'
                    v-for="book in familyBooks"
                    v-bind:key="book.index"
                    :value="book.book_id">
                    {{ book.title }}
                  </option>
                </select>
              </div>
            </div>
            <div class="form-group-narrow right">
              <label for="ChildReadingReview" class="label-title">Review</label
              ><br />
              <textarea
                id="childreadingreview"
                name="ChildReadingReview"
                rows= "7"
                cols="40"
                v-model="userBook.review"
              /><br />
            </div>
          </div>
          <!-- Format, Minutes Read, Notes-->
          <div class="horizontal-group">
            <div class="form-group-wide left">
              <div class="split-form-one-group-left">
                <label for="MinutesRead" class="label-title"
                  >Minutes Read</label
                >
                <input
                  type="text"
                  id="minutesread"
                  name="MinutesRead"
                  v-model="userBook.minutes_read"
                />
              </div>
              <div class="split-form-two-group-left">
                <label for="BookFormat" class="label-title">Format</label>
                <select
                  class="drop-down"
                  name="BookFormat"
                  id="bookformat"
                  v-model="userBook.reading_format"
                  autofocus
                >
                  <option class='links' disabled hidden value="">Pick Book Format</option>
                  <option class='links' selected value="Paper">Paper</option>
                  <option class='links' value="Digital">Digital</option>
                  <option class='links' value="Audiobook">Audiobook</option>
                  <option class='links' value="ReadAloudReader">Read-Aloud (Reader)</option>
                  <option class='links' value="ReadAloudListener">
                    Read-Aloud (Listener)
                  </option>
                  <option class='links' value="Other">Other</option>
                </select>
              </div>
            </div>
            <div class="form-group-narrow right"> 
            </div>
          </div>
        </div>
        <div class="form-footer">
           <label for="FinishedBookCheckbox" class="label-title">Finished Book?</label>
              <input
                type="checkbox"
                id="finishedbookcheckbox"
                v-model="userBook.times_read"
                value="true"
              />
          <button class="btn" type="submit" v-on:click="LogReading">
            Submit
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import BookService from "@/services/BookService.js";

export default {
  name: "ReadingLog",
  props: ["familyUsers"],
  data() {
    return {
      userBook: {
        user_id: "",
        book_id: "",
        minutes_read: "",
        reading_format: "Paper",
        times_read: "",
        review: "",
        session_points: 0
      },
      familyBooks: [],
      currentUser: this.$store.state.user,
    };
  },
  computed: {
    isParent: function () {
      if (this.$store.state.user.authorities[0].name == "ROLE_PARENT") {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    LogReading() {
      console.log("Checking for userID" + this.userId);
      if (this.userBook.times_read === true) {
        this.userBook.times_read = 1;
      }
      if (this.userBook.user_id === ""){
          this.userBook.user_id = this.currentUser.id
      }
      BookService.logReading(this.userBook);
      BookService.getAllBooksByFamily(this.$store.state.user.family_id).then(
        (response) => {
          this.familyBooks = response.data;
        }
      );
    },
  },

  created() {
    BookService.getAllBooksByFamily(this.$store.state.user.family_id).then(
      (response) => {
        this.familyBooks = response.data;
      }
    );
  },
};
</script>


<style>
.split-form-one-group-left-child{
  float:left;
  width: 50%; 
  margin-left: 0;
  padding-left: 0;
}
.drop-down{
  border: 2px;
  background: #ffffff;
  outline: 3px;
  color: #222222;
  font-family: 'League Spartan', sans-serif;
  text-align: left;
  margin-top: 8px;
  padding: 6px 16px;
  text-decoration: none;
  font-size: 15px;
}
select {
  line-height: 2px;
}
option{
  min-height: 1.4em;
  padding: 1px 3px 6px;
}
#readers {
  width: 140px;
  margin-bottom: 15px;
}
#minutesread {
  margin-top: 16px;
  width: 80px;
  margin-left: 5px;
}
#assignedbooks {
  width: 140px;
}
#bookformat {
  width: 140px;
  margin-top: 16px;
}
#genre{
  width: 90px;
  margin-right: 0px;
}
#difficulty{
  width: 90px;
}
.label-kid{
  margin-top: 1px;
  margin-bottom: 1px;
  text-align:left; 
  font-size:42px; 
  text-transform:uppercase; 
  color:#222; 
  letter-spacing:1px;
  font-family: 'Bubblegum Sans', cursive;
    /* Shadows are visible under slightly transparent text color */
    color: rgba(7, 50, 61, 0.85);
    /* text-shadow: 1px 2px 4px #eef1f1, 0 0 0 #000, 1px 2px 3px #183c41; */
}
</style>