import Ember from "ember";
import UnauthenticatedRouteMixin from "ember-simple-auth/mixins/unauthenticated-route-mixin";

export default Ember.Route.extend(UnauthenticatedRouteMixin, {
  usersService: Ember.inject.service(),

  actions: {
    authenticateByCredentials (credentials) {
      this.get('usersService')
        .authenticate(credentials)
        .then(() => this.transitionTo('dashboard'));
    }
  }
});
