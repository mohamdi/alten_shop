import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {NgIf} from "@angular/common";
import {ButtonDirective} from "primeng/button";

@Component({
    selector: 'app-contact-form',
    templateUrl: './contact-form.component.html',
    styleUrls: ['./contact-form.component.scss'],
    standalone: true,
    imports: [
        ReactiveFormsModule,
        NgIf,
        ButtonDirective
    ]
})
export class ContactFormComponent {
    contactForm: FormGroup;
    successMessage: string = '';
    isSubmitted: boolean = false;

    constructor(private fb: FormBuilder) {
        this.contactForm = this.fb.group({
            email: ['', [Validators.required, Validators.email]],
            message: ['', [Validators.required, Validators.maxLength(300)]]
        });
    }

    get email() {
        return this.contactForm.get('email');
    }

    get message() {
        return this.contactForm.get('message');
    }

    onSubmit() {
        this.isSubmitted = true;
        if (this.contactForm.valid) {
            this.successMessage = 'Demande de contact envoyée avec succès';
            this.contactForm.reset();
            this.isSubmitted = false;
        }
    }
}
