import Ember from "ember";

const {inject: {service}} = Ember;

export default Ember.Service.extend({
  session: service(),
  ajax: service(),
  account: null,

  loadCurrentUser() {
    return new Ember.RSVP.Promise((resolve, reject) => {
      const token = this.get('session.data.authenticated.token');
      if (Ember.isPresent(token)) {
        const options = Ember.getOwner(this).lookup("adapter:application").ajaxOptions();
        return this.get('ajax').request('api/user/current', options).then((user) => {
          this.set('account', user);
          resolve();
        }, reject);
      } else {
        resolve();
      }
    });
  }
});
