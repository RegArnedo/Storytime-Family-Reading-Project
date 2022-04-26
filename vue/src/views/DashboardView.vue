<template>
  <div>
    <div v-if="isParent">
      <parent-dashboard />
    </div>
    <div v-else>
      <child-dashboard />
    </div>
  </div>
</template>

<script>
import ParentDashboard from "@/components/ParentDashboard";
import ChildDashboard from "../components/ChildDashboard.vue";

export default {
  components: {
    ParentDashboard,
    ChildDashboard,
  },
  computed: {
    currentUserId: function () {
      return this.$store.state.user.id;
    },
    currentRole: function () {
      return this.$store.state.user.authorities[0].name;
    },
    isParent: function () {
      if (this.$store.state.user.authorities[0].name == "ROLE_PARENT") {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>