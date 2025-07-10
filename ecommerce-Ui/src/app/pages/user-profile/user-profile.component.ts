import {Component, inject} from '@angular/core';
import Keycloak from 'keycloak-js';
import {User} from '../../core/interfaces/model';

@Component({
  selector: 'app-user-profile',
  imports: [],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss'
})
export class UserProfileComponent {
  private readonly keycloak = inject(Keycloak);

  user: User | undefined;

  async ngOnInit() {
    if (this.keycloak?.authenticated) {
      const profile = await this.keycloak.loadUserProfile();

      this.user = {
        name: `${profile?.firstName} ${profile.lastName}`,
        email: profile?.email,
        username: profile?.username
      };
    }
  }
}
