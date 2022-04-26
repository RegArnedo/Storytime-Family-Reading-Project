<template>
  <div>
    <div class="title">
      <h1>Create Account<span>Keep track of your kid's reading. Build habits for life.</span></h1>
    </div>
  <div class="register-flex">
    <div class="photo">
      <img src="@/assets/tiger.png" />
    </div>
  <div>
    <registration-form />
  </div>
  </div>
  </div>
</template>

<script>
import RegistrationForm from '../components/RegistrationForm.vue'
export default {
  
  components: { RegistrationForm },
}
</script>

<style>
.register-flex{
  display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>
