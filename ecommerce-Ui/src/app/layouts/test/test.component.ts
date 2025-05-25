import {Component, ElementRef, HostListener, inject} from '@angular/core';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-test',
  imports: [
    NgIf
  ],
  templateUrl: './test.component.html',
  styleUrl: './test.component.scss',
  standalone: true
})
export class TestComponent {
  dropdownOpen = false;
  private host = inject(ElementRef).nativeElement as HTMLElement;

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  // close when clicking outside
  @HostListener('document:click', ['$event.target'])
  onClick(target: HTMLElement) {
    if (this.dropdownOpen && !this.host.contains(target)) {
      this.dropdownOpen = false;
    }
  }


}
