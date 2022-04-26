<template>
<div id="app">
  <div class="home-left">
    <div class="topnav" id="myTopnav">
      <router-link class='container' v-bind:to="{ name: 'home' }">HOME </router-link>
      <router-link class='container' v-bind:to="{ name: 'dashboard', params: {userId: currentUserId}}">DASHBOARD</router-link> 
      <router-link class='container' v-bind:to="{ name: 'bookshelf', params: {userId: currentUserId}}">BOOKSHELF</router-link> 
      <router-link class='container' v-bind:to="{ name: 'reading-history'}">READING-HISTORY</router-link> 
    <div class="dropdown">
      <button class="dropbtn">GO TO:
        <i class="fa fa-caret-down"></i>
      </button>
    <div class="dropdown-content">
      <router-link  v-bind:to="{ name: 'register' }">REGISTER</router-link>  
      <router-link  v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">LOGOUT</router-link> 
      <router-link  v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">LOGIN</router-link> 
    </div>
    </div>
      <div class="home-right">
        <img src="@/assets/story_time.png" />
      </div>
    </div>
</div>
    <router-view />
</div>
</template>

<script>
// import { defineComponent } from '@vue/composition-api'

export default {
  name: 'app',
  data() {
    return {
    }
  },
  computed: {
    currentUserId: function(){
      return this.$store.state.user.id
    },
    currentRole: function(){
      return this.$store.state.user.authorities[0].name
    }
  },
  methods: {
   
  }

}
</script>


<style >

/*---------------------------------------*/
/* */
/*---------------------------------------*/

/*---------------------------------------*/
/* Web Font*/
/*---------------------------------------*/
@import url('https://fonts.googleapis.com/css2?family=League+Spartan&display=swap');

/*---------------------------------------*/
/* Top Banner */
/*---------------------------------------*/
.home-right{
  padding: 11px 20px;
  padding-left: 955px;
}
.topnav {
    display: flex;
}
.push {
    margin-left: auto;
    justify-content: flex-end;
}
/* Style the links inside the navigation bar */
.topnav a {
  color: #222222;
  float: left;
  font-family: "Arial Black", Gadget, sans-serif;
  text-align: center;
  padding: 16px 20px;
  text-decoration: none;
  font-size: 18px;
}
/* Change the color of links on hover */
.topnav a:hover {
  background-color: #f9f9f9;
  color: #222222;
}
.topnav .dropdown {
  overflow: hidden;
}

/* Dropdown button */
.topnav .dropdown .dropbtn {
  border: none;
  background: inherit;
  outline: none;
  color: #222222;
  float: left;
  font-family: "Arial Black", Gadget, sans-serif;
  text-align: center;
  margin-top: 10px;
  padding: 6px 16px;
  text-decoration: none;
  font-size: 18px;
}

/* Dropdown content (hidden by default) */
.topnav .dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  z-index: 1;
}

/* Links inside the dropdown */
.topnav .dropdown-content a {
  float: none;
  color: #222222;
  margin-top: 4px;
  padding: 12px 16px;
  text-decoration: none;
  text-align: left;
  display: block;
}

/* Add a background color to dropdown links on hover */
.topnav .dropdown-content a:hover {
  background-color: rgba(187, 218, 218, 0.8);
}
/* Show the dropdown menu on hover */
.topnav .dropdown:hover .dropdown-content {
  background-color: #f9f9f9;
  display: block;
}
/*---------------------------------------*/
/* Title and Sub-text */
/*---------------------------------------*/
.title h1{
  margin-top:65px;
  margin-bottom: 15px;
  text-align:center; 
  font-size:70px; 
  text-transform:uppercase; 
  color:#222; 
  letter-spacing:1px;
  font-family: 'Bubblegum Sans', cursive;
    /* Shadows are visible under slightly transparent text color */
    color: rgba(7, 50, 61, 0.85);
    text-shadow: 1px 2px 4px #eef1f1, 0 0 0 #000, 1px 2px 3px #183c41;
}
.title h1 span {
  margin-top: 5px;
    font-size:25px; 
    color:#444; 
    word-spacing:1px; 
    font-weight:normal; 
    letter-spacing:2px;
    text-transform: uppercase; 
    font-family: 'Bubblegum Sans', cursive;
    display: grid;
    grid-template-columns: 1fr max-content 1fr;
    grid-template-rows: 27px 0;
    grid-gap: 20px;
    align-items: center;
    box-shadow: 4px rgba(0,0,0,0.10),4px rgba(0,0,0,0.08);
}
.title h1 span:after,.title h1 span:before {
    content: " ";
    display: block;
    border-bottom: 1px solid #ccc;
    border-top: 1px solid #ccc;
    height: 5px;
    background-color:#f8f8f8;
}
body{
    font-family: 'League Spartan', sans-serif;
    font-size: 20px;
    background: linear-gradient(to right, #8ab9bc 0%, #b0dde0 50%, #8ab9bc 99%);
}
/*---------------------------------------*/
/* Forms */
/*---------------------------------------*/
.form-register{
  font-family: 'League Spartan', sans-serif;
    margin: 30px auto;
    background: linear-gradient(to right, #e9f8fa 0%, #fafafa 50%, #ffffff 99%);
    border-radius: 10px;
    box-shadow: 4px rgba(0,0,0,0.10),4px rgba(0,0,0,0.08);
    width: 1050px;
}
.form-login{
  font-family: 'League Spartan', sans-serif;
    width: 850px;
    margin: 30px auto;
    background: linear-gradient(to right, #e9f8fa 0%, #fafafa 50%, #ffffff 99%);
    border-radius: 10px;
    box-shadow: 4px rgba(0,0,0,0.10),4px rgba(0,0,0,0.08);
}
.form-add-book{
    font-family: 'League Spartan', sans-serif;
    background: linear-gradient(to right, #e9f8fa 0%, #fafafa 50%, #ffffff 99%);
    border-radius: 10px;
    box-shadow: 4px rgba(0,0,0,0.10),4px rgba(0,0,0,0.08);
}
.form-reading-log{
  font-family: 'League Spartan', sans-serif;
    background: linear-gradient(to right, #e9f8fa 0%, #fafafa 50%, #ffffff 99%);
    border-radius: 10px;
    box-shadow: 4px rgba(0,0,0,0.10),4px rgba(0,0,0,0.08);
}
.form-header {
    height: 50px;
    background-color: rgb(209, 230, 214);
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    padding-left: 20px;
    padding-top: 20px
}
.form-header{
    font-size: 36px;
    font-family: 'League Spartan', sans-serif;
    color: #313131;
    text-shadow: 1px 4px 6px #9dc0c0, 0 0 0 rgb(51, 53, 53), 1px 1px 1px #2a656d;  
}
/*---------------------------------------*/
/* Form Body */
/*---------------------------------------*/
.form-register .form-body {
    margin: 30px;
    height: 160px;
}
.form-add-book .form-body {
    margin: 15px;
    height: 110px;
}
.form-reading-log .form-body{
    margin: 10px;
    height: 150px;
}
.form-body {
    margin: 10px;
    padding: 20px;
    height: 40px;
}
.form-group {
    margin-bottom: 35px;
}
.form-group-wide {
  width:58%;
}
.form-group-narrow{
  width:41%;
}
.form-body .label-title {
    color: #157788;
    font-size: 20px;
    font-weight: bold;
    padding-right: 10px;  
    padding-left: 10px;
}
.form-body .form-input {
    font-size: 14px;
    box-sizing: border-box;
    width: 55%;
    height: 30px;
    padding-left: 10px;
    padding-right: 10px;
    color: #333333;
    text-align: left;
    border: 1px solid #d6d6d6;
    border-radius: 6px;
    background: white;
    outline: none;
    
}
.form-body .form-password {
    font-size: 14px;
    box-sizing: border-box;
    width: 35%;
    height: 30px;
    padding-left: 10px;
    padding-right: 10px;
    color: #333333;
    text-align: left;
    border: 1px solid #d6d6d6;
    border-radius: 4px;
    background: white;
    outline: none;
}
.horizontal-group .left {
    float: left;
    width: 53%;
}
.horizontal-group .right {
    float: right;
    width: 47%;
}
.split-form-one-group-right{
  float:right;
  width: 50%; 
}
.split-form-two-group-right{
  float: right;
  width: 49%;
}
.split-form-one-group-left{
  float:left;
  width: 45%; 
  margin-left: 0;
  padding-left: 0;
}
.split-form-two-group-left{
  float: left;
  width: 55%;
  margin: 0%;
}
.btn-body {
    display: inline-block;
    padding: 10px 10px;
    background-color: #157788;
    font-size: 17px;
    border: none;
    border-radius: 10px;
    color: #bcf5e7;
    cursor: pointer;
}
.btn-body:hover {
    background-color: #1fc1dd;
    color: white;
    box-shadow: 0 4px 4px 0 rgba(0,0,0,0.10),0 5px 5px 0 rgba(0,0,0,0.08);
}
/*---------------------------------------*/
/* Form Footer */
/*---------------------------------------*/
.form-footer {
    height: 30px;
    background-color: rgb(209, 230, 214);
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    text-align: right;
    padding: 18px;
    border-top: 1px solid #cccccc;
    box-shadow: 0 4px 4px 0 rgba(0,0,0,0.10),0 5px 5px 0 rgba(0,0,0,0.08);
}
.form-footer span {
    float: right;
    margin-top: 10px;
    color: rgb(204, 38, 38);
    font-style: italic;
    font-weight: thin;
}
.form-footer .btn {
    border: 10px;
    float: right;
    padding: 8px;
    padding-left: 25px;
    padding-right: 25px;
    margin-left: 50px;
    margin-right:50px;
    margin-bottom: 20px;
    background-color: #1791a7;
    font-size: 17px;
    border: none;
    border-radius: 10px;
    color: #ffffff;
    cursor: pointer;  
}
.btn:hover {
    background-color: #22aac2;
    color: white;
    box-shadow: 0 4px 4px 0 rgba(0,0,0,0.10),0 5px 5px 0 rgba(0,0,0,0.08);
}
.btn {border: 10px;
    float: left;
    padding: 8px;
    padding-left: 25px;
    padding-right: 25px;
    margin-left: 50px;
    margin-right:50px;
    background-color: #157788;
    font-size: 17px;
    border: none;
    border-radius: 10px;
    color: #ffffff;
    cursor: pointer;  
    box-shadow:inset 0 -0.6em 1em -0.35em rgba(0,0,0,0.17),inset 0 0.6em 2em -0.3em rgba(255,255,255,0.15),inset 0 0 0em 0.05em rgba(255,255,255,0.12);
}


/*---------------------------------------*/
/* Disorganized chaos*/
/*---------------------------------------*/
.drop-down{
  margin-left: 10px;
}
h4, h5, h6{
  font-family: 'League Spartan', sans-serif;
}
h4{
  font-size: 29px;
  font-weight:bold;
}
h1{
  margin-bottom: 6px;
}
</style>
