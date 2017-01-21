import Ember from "ember";
import UnauthenticatedRouteMixin from "ember-simple-auth/mixins/unauthenticated-route-mixin";

export default Ember.Route.extend(UnauthenticatedRouteMixin, {
  session: Ember.inject.service(),

  actions: {
    authenticateByCredentials ({email, password}) {
      const authenticator = 'authenticator:jwt';

      this.get('session')
        .authenticate(authenticator, {identification: email, password})
        .then(() => {
          this.transitionTo('dashboard');
        });
    }
  }
});
