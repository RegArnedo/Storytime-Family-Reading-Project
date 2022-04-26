<template>
  
  <div class="child-register">    
    <form class="form-register" @submit.prevent="register">
    <div>
      <h1 class="form-header">New Family Member Registration:</h1>
    </div>
    <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
    </div>
  <div class="form-body">
            <!-- Firstname and Lastname -->
            <div class="horizontal-group">
                <div class="form-group left">
                    <label for="firstname" class="label-title">First Name *</label>
                    <input
                        type="text"
                        id="firstname"
                        class="form-input"
                        placeholder="First Name"
                        v-model="user.first_name"
                        required
                        autofocus
                    />
                    </div>
                <div class="form-group right">
                    <label for="lastname" class="label-title">Last Name *</label>
                    <input
                        type="text"
                        id="lastname"
                        class="form-input"
                        placeholder="Last Name"
                        v-model="user.last_name"
                        required
                        autofocus
                    />
                    </div>
            </div>
            <!-- Email -->
            <div class="horizontal-group">
                <div class="form-group">
                    <div class="form-group left">
                        <label for="email" class="label-title">Email Address </label>
                        <input
                            type="text"
                            id="email"
                            class="form-input"
                            placeholder="Valid Email Address"
                            v-model="user.email"
                            required
                            autofocus
                        />
                    </div>
                    <div class="form-group right">
                        <label for="username" class="label-title">Username *</label>
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
                </div>
            </div>
                <div class="horizontal-group">
                  <div class="form-group left">
                      <label for="password" class="label-title">Password *</label>
                      <input
                          type="password"
                          id="password"
                          class="form-password"
                          placeholder="Password"
                          v-model="user.password"
                          required
                      />
                      <input
                          type="password"
                          id="confirmPassword"
                          class="form-password"
                          placeholder="Confirm Password"
                          v-model="user.confirmPassword"
                          required
                      />
                  </div>
                  <div class="form-group right">
                    <input type="checkbox" class="isParent" name="isParent" value="isParent">
                    <label for="isParent">Add as additional parent? </label>
                  </div>
                </div>
  </div>
        <div class="form-footer">
            <span>* Required</span>
              <button class="btn" type="submit">
              Create Account
              </button>
        </div>
    </form>
</div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register-child',
  data() {
    return {
      user: {
        first_name: '',
        last_name: '',
        email: '',
        username: '',
        password: '',
        confirmPassword: '',
        role: 'child',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  computed: {
    currentUserId: function(){
      return this.$store.state.user.id
    }
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        console.log('regFamUser: '+this.user)
        console.log('regCurrentUserId: '+this.currentUserId)
        authService
          .registerFamilyUser(this.user, this.currentUserId)
          .then((response) => {
            if (response.status == 201) {
              alert('Child account created!  Hurray for our side!'),
              this.user = {
                first_name: '',
                last_name: '',
                email: '',
                username: '',
                password: '',
                confirmPassword: '',
                role: 'child',
              },  
             <v-alert
              color="blue"
              dense
              elevation="9"
              icon="$mdiAccount"
              prominent
              type="success"
            ></v-alert>
            }
          })
          .catch((error) => {
            console.log('regFamUser error: '+error)
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
.isParent{
  margin: 10px;
  margin-left: 10%;
  font-size: 18px;
  font-weight: bold;
  padding-right: 18px; 
}


</style>