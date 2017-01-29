import Ember from "ember";

export default Ember.Controller.extend({
  usersService: Ember.inject.service(),

  name: null,
  email: null,
  password: null,

  errors: null,

  actions: {
    register () {
      this.set('errors', null);
      const registrationInfo = this.getProperties('name', 'email', 'password');
      this.get('usersService').register(registrationInfo)
        .then(() => this.transitionToRoute('login'))
        .catch(errors => {
          this.set('errors', errors);
        });
    }
  }
});
