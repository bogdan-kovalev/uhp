import Ember from "ember";
import {Types} from "../models/types";

const {inject: {service}} = Ember;

export default Ember.Service.extend({
  session: service(),
  ajax: service(),
  store: service(),
  account: null,

  register (registrationInfo) {
    const record = this.get('store').createRecord(Types.RegistrationInfo, registrationInfo);
    return new Ember.RSVP.Promise((resolve, reject) => {
      record.save()
        .then(() => {
          resolve();
        })
        .catch((err) => {
          console.log(err);
          reject(err instanceof Ember.Error ? [{message: "An unexpected error have happened. Try to reload the page."}] : record.get('errors'));
        });
    });
  },

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
