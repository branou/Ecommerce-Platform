import {Component, ElementRef, HostListener, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from "@angular/forms";
import {Router, RouterLink, RouterLinkActive} from '@angular/router';

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

  constructor(private router: Router) {}



  openMegaMenu(category: string) {
    this.isMegaMenuOpen = true;
    this.activeMegaMenu = category;
  }

  closeMegaMenu() {
    this.isMegaMenuOpen = false;
    this.activeMegaMenu = null;
  }

  search() {
    if (this.searchTerm.trim()) {
      this.router.navigate(['/search'], { queryParams: { q: this.searchTerm } });
      this.searchTerm = '';
      this.closeMegaMenu();
      this.isMenuOpen = false;
    }
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

}
