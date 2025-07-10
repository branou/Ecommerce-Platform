import {Component, effect, inject} from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {KEYCLOAK_EVENT_SIGNAL, KeycloakEventType} from 'keycloak-angular';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  standalone: true
})
export class AppComponent {
  title = 'ecommerce-Ui';
  private readonly keycloakSignal = inject(KEYCLOAK_EVENT_SIGNAL);
  router = inject(Router)
  constructor() {
  }
}
