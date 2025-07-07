import {Injectable} from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  private _keycloak!:Keycloak | undefined;
  constructor() { }

  get keycloak(){
    if(!this._keycloak){
      this._keycloak = new Keycloak({
        url : 'http://localhost:8080',
        realm : 'myRealm',
        clientId : 'ecommerce-frontend'
      });
    }
    return this._keycloak;
  }
  async init(): Promise<void> {
    console.log('authenticating the user ...');
    try {
      const authenticated = await this.keycloak.init({
        onLoad: 'login-required',
        checkLoginIframe: false,
      });

      if (authenticated) {
        console.log('✅ User authenticated successfully');
      } else {
        console.warn('❌ User not authenticated');
      }
    } catch (err) {
      console.error('Keycloak init failed:', err);
      throw err;
    }
  }


}
