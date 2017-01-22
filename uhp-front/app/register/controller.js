import Ember from "ember";

export default Ember.Controller.extend({
  usersService: Ember.inject.service(),

  name: null,
  email: null,
  password: null,

  actions: {
    register () {
      const registrationInfo = this.getProperties('name', 'email', 'password');
      this.get('usersService').register(registrationInfo).then(() => this.transitionToRoute('login'));
    }
  }
});
