import Ember from "ember";

export default Ember.Controller.extend({
  actions: {
    authenticate () {
      const credentials = this.getProperties('email', 'password');
      this.send('authenticateByCredentials', credentials);
    }
  }
});
