import {Component, effect, ElementRef, HostListener, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from "@angular/forms";
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {Store} from '@ngrx/store';
import {search} from '../../core/store/product/product.actions';
import Keycloak from 'keycloak-js';
import {KEYCLOAK_EVENT_SIGNAL, KeycloakEventType, ReadyArgs, typeEventArgs} from 'keycloak-angular';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
  standalone: true
})
export class NavbarComponent {
  dropdownOpen = false;
  private host = inject(ElementRef).nativeElement as HTMLElement;

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  // close when clicking outside
  searchTerm: any;
  @HostListener('document:click', ['$event.target'])
  onClick(target: HTMLElement) {
    if (this.dropdownOpen && !this.host.contains(target)) {
      this.dropdownOpen = false;
    }
  }
  isMenuOpen = false;
  isMegaMenuOpen = false;
  activeMegaMenu: string | null = null;
  authenticated = false;
  keycloakStatus: string | undefined;
  private readonly keycloak = inject(Keycloak);
  private readonly keycloakSignal = inject(KEYCLOAK_EVENT_SIGNAL);

  constructor(private router: Router, private store: Store) {
    effect(() => {
      const keycloakEvent = this.keycloakSignal();

      this.keycloakStatus = keycloakEvent.type;

      if (keycloakEvent.type === KeycloakEventType.Ready) {
        this.authenticated = typeEventArgs<ReadyArgs>(keycloakEvent.args);
      }

      if (keycloakEvent.type === KeycloakEventType.AuthLogout) {
        this.authenticated = false;
      }
    });
  }



  openMegaMenu(category: string) {
    this.isMegaMenuOpen = true;
    this.activeMegaMenu = category;
  }

  closeMegaMenu() {
    this.isMegaMenuOpen = false;
    this.activeMegaMenu = null;
  }
  page: number = 0;
  size: number = 10;
  search() {
    const term = this.searchTerm.trim();
    if (term) {
      this.router.navigate(['/search'], { queryParams: { q: term } });
      this.store.dispatch(search({ term, page: this.page, size: this.size }));

      this.searchTerm = '';
      this.closeMegaMenu();
      this.isMenuOpen = false;
    }
  }


  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  gotowishlist() {
    this.router.navigate(['/wishlist'])
  }

  login() {
    this.keycloak.login();
  }

  logout() {
    this.keycloak.logout();
  }

  gotoOrderHistory() {
    this.router.navigate(['/order-history'])
  }
}
