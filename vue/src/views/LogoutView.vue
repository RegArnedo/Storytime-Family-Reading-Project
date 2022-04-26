<template>
  <div>
  </div>
</template>

<script>
// import AppHeader from "./components/Appheader.vue";

export default {
  // AppHeader,
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/");
  }
};
</script>
