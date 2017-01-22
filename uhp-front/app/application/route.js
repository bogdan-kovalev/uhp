import Ember from "ember";
import ApplicationRouteMixin from "ember-simple-auth/mixins/application-route-mixin";

export default Ember.Route.extend(ApplicationRouteMixin, {
  usersService: Ember.inject.service(),
  beforeModel() {
    return this._loadCurrentUser();
  },
  sessionAuthenticated() {
    this._loadCurrentUser()
      .then(() => {
        this.transitionTo('dashboard');
      })
      .catch(() => this.get('session').invalidate());
  },
  _loadCurrentUser() {
    return this.get('usersService').loadCurrentUser();
  }
});
