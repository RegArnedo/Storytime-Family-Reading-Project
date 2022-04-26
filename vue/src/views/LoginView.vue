<template>
            
  <div id="login" class="text-center">
    <div class="title">
      <h1>Member Login<span>Keep track of your kid's reading. Build habits for life.</span></h1>
    </div>
    <div class="register-flex">
      <div>
      <form class="form-login" @submit.prevent="login">
        <h1 class="form-header">Please Sign In</h1>
        <div
          class="alert alert-danger"
          role="alert"
          v-if="invalidCredentials"
        >Invalid username and password!</div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >Thank you for registering, please sign in.</div>
        <div class="form-body">
          <div class="horizontal-group">
              <div class="form-group left">
                    <label for="username" class="label-title">Username</label>
                    <input
                      type="text"
                      id="username"
                      class="form-input"
                      placeholder="Username"
                      v-model="user.username"
                      required
                      autofocus
                    />
              </div>       
              <div class="form-group right">
                <label for="password" class="label-title">Password</label>
                <input
                type="password"
                id="password"
                class="form-input"
                placeholder="Password"
                v-model="user.password"
                required
              />
              </div>
          </div>
          </div>
          <div class="form-footer">
            <button class='btn' type="submit">Sign in</button>
          </div>
      </form>
      </div>
      <div class="photo">
        <img src="@/assets/login.png" />
      </div>
  </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/parent/dashboard/"+response.data.user.id);
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
