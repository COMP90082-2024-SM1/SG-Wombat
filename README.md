# SG-Wombat

Welcome to the Science Gallery Booking System! This system aims to streamline the process of managing bookings for various engaging and educational experiences offered to secondary school groups. These experiences include free gallery tours and paid interactive workshops.

## Github Structure

### docs
The docs folder includes all the Confluence pages. We export our Confluence pages as PDFs and upload them to the docs folder. The structure of docs mirrors that of Confluence. Some links in the PDFs are not clickable; therefore, we include those links in the README file for easier access by readers.

### src
The src folder is where we commit all our code. It was initiated for use in sprint 2.

### README 

The README file is constantly updated to explain our GitHub structure and generate changelogs for each sprint before tagging it.

## Product

[Science Gallery Booking System URL](http://18.208.189.107/)

## Important Documents

### Confluence Page:https://comp90082-2024-sg-wombat.atlassian.net/wiki/x/2AAI

### Prototype Link:https://js.design/v?i=0G-ElJ&p=7MfmZw5wEX&f=0:1

### Final Presentation Link: https://prezi.com/view/2IAx6UP27neaLNVbOsML/


## Background

The Science Gallery provides diverse educational experiences for secondary school groups, from free gallery tours to paid workshops like drone flying and 3D bone assembly. However, managing booking requests has become challenging due to their various sources (calls, Microsoft platforms, email) and the complexity of finalizing bookings across multiple venues with differing requirements. With numerous programs and teams using different booking forms and software, the process is labor-intensive, handling hundreds of appointments annually, including cancellations. To streamline operations, the Science Gallery seeks to enhance its system by accurately recording bookings, actions required, and improving workflow efficiency.

## Goal

The primary goal of this project is to develop a comprehensive booking management system for the Science Gallery, aimed at enhancing operational efficiency and improving user experience for both partner and non-partner school teachers. This system will enable teachers to easily book programs, manage bookings, and access educational experiences, while providing Science Gallery staff with the necessary tools to streamline booking processes, manage resources effectively, and ensure accurate record-keeping. Additionally, the system will support the expansion of program offerings, facilitate space management, and enable data-driven decision-making through comprehensive reporting capabilities. Overall, the goal is to create a user-friendly, efficient, and scalable platform that enhances the educational outreach efforts of the Science Gallery and optimizes the visitor experience.

## Project Workflow

This section outlines our workflow for managing code changes in our project repository on GitHub. We aim to maintain a clean, efficient workflow that ensures code quality and a clear history of changes.

### Branching Strategy
**Feature Branches:**
- We create branches based on the features being developed to keep our work organized and isolated from the main codebase. This approach allows multiple features to be developed in parallel without interference.

**Naming Convention:**
- Branch names should be descriptive and reflect the feature or bug fix they are intended to address. Our naming convention is as follows:
  - For features: `feature/<feature-name>`
  - For bug fixes: `fix/<bug-name>`
  - For hotfixes: `hotfix/<hotfix-name>`
  - Example: `feature/login-authentication`

### Code Integration Process
1. **Pull Request:**
   - Once a feature or fix is completed on a branch, a pull request (PR) is created to merge the changes into the master branch.
   - The PR title should clearly describe the change or enhancement and, if applicable, reference the related issue or task.

2. **Code Review:**
   - Every PR must be reviewed by at least one other team member before merging. This ensures that at least two sets of eyes have vetted the code, which helps maintain code quality and reduce bugs.
   - Reviewers should check for code quality, adherence to project standards, functionality, and integration with existing code.
   - Reviewers can comment, approve, or request changes. PRs should only be merged after receiving approval.

3. **Automated Tests:**
   - We are currently facing challenges with our automated testing setup, which are preventing us from running automated tests as part of our pull request process. We are actively working to resolve these issues to ensure that our testing framework integrates smoothly with our development workflow.

4. **Merge and Clean-up:**
   - Once approved and all tests pass, the PR is merged into the master branch.
   - After the merge, the feature branch should be deleted to keep the repository clean and manageable. This deletion should be part of the PR process.

### Best Practices
- Keep your branches up-to-date with the master branch to minimize merge conflicts.
- Regularly push your changes to remote branches to avoid losing work and to keep teammates informed of your progress.
- Use clear and descriptive commit messages that explain why the change was made.

By adhering to this workflow, we ensure that our codebase remains clean and stable, and that our project progresses efficiently. This process also fosters collaboration and code quality assurance through peer reviews and automated testing.

### Branches

- **Main Branch**: The main branch contains stable, production-ready code.
- **Test Branch**: The test branch is used for testing new features and fixes before merging into the main branch.
- **Frontend Branch**: Development work related to frontend features and enhancements is conducted on this branch.
- **Backend Branch**: Development work related to backend features and enhancements is conducted on this branch.

## Sprint 1

### Trello Link: https://trello.com/invite/b/8dly4T4G/ATTIc3f682ec9dcafd3842c9ef68338fe45d7011A3EA/sg-booking-system-sprint-1

### What we have done:
At our core, we engage in client communication to initiate projects through kick-off meetings. Following this, we meticulously delve into Requirements Engineering, beginning with eliciting requirements from clients through various channels such as discussions, meetings, and emails. Our process further involves Motivational Modeling, where we develop Do-be-feel lists, create motivational models, and craft personas to deeply understand user needs. Subsequently, we analyze requirements, refining them through discussions on draft prototypes, technology stack considerations, and more. We then translate these requirements into actionable User Stories, estimating their size and prioritizing them based on client requirements and importance. Moving into the Design phase, we create initial digital prototypes of the booking management system, seeking stakeholder feedback to refine our designs. Additionally, we meticulously plan for upcoming development sprints, determining which features will be delivered and when. Finally, we ensure smooth transitions into Design and Development phases by setting up necessary environments, including Confluence, Slack, Trello, and Github, to facilitate collaboration and project management.

### Changelog

This section outlines the significant changes and updates made during Sprint 1 of our project. These entries provide insights into the development progress and highlight the key features and fixes introduced.

#### Features
- **User Story Implementation:**
  - Completed the implementation of critical user stories which include the setup of the basic project architecture and foundational features.
  - Updated user stories with acceptance criteria to ensure clarity and measurable outcomes.

- **Prototype Development:**
  - Developed and demonstrated an initial prototype featuring basic navigation and page layouts to solicit early feedback from stakeholders.

#### Technical Updates
- **Frontend Transition:**
  - Transitioned the frontend technology from React to Vue to leverage Vue's capabilities in building more interactive and dynamic user interfaces.

- **Database Migration:**
  - Migrated the database system from MongoDB to MySQL to enhance data management and query performance.

#### Code Reviews
- Established a rigorous code review process to ensure all contributions are thoroughly vetted for quality and adherence to project standards before merging.

#### Challenges and Fixes
- **Automated Testing Setup:**
  - Encountered issues with setting up automated tests as part of our CI/CD pipeline. Efforts are ongoing to resolve these challenges and integrate a robust testing framework.

- **Icon Importing Issues:**
  - Faced difficulties with importing icons into the frontend. The issue was systematically addressed by modifying the asset pipeline and ensuring compatibility with the Vue framework.

#### Documentation and Cleanup
- **Code Merge and Cleanup:**
  - Successfully merged all feature branches into the master branch post-review.
  - Implemented a clean-up procedure to delete feature branches after merging to maintain a tidy repository environment.

#### Next Steps
- Continue to resolve the automated testing issues.
- Further refine the prototype based on feedback and prepare for more comprehensive user testing in Sprint 2.

This changelog reflects our commitment to transparency and continuous improvement. We look forward to further enhancements and more detailed functionality in the upcoming sprints.

## Sprint 2

### Trello Link:https://trello.com/invite/b/Uis4BYVJ/ATTI361288635efd2b002e8e41b2300ce5678856509D/sg-booking-system-sprint-2


### What we will do:
In Sprint 2, we aim to establish the foundational features of the science gallery booking system, including booking request submission, program management and user log-in. By the end of this sprint, we plan to develop the interface for non-partner school teachers to submit their program requests. In addition, we will implement the log-in function for science gallery staff, as well as the list of programs, which is an essential part of the bookings.

As the first development sprint, we also need to lay the groundwork for the system, which involves system design, environment configuration, and constructing the structure of the booking system.

### Sprint 2 Changelog

This section captures the essential updates and developments from Sprint 2 of our project. It outlines the advancements made, technical challenges addressed, and the new functionalities introduced.

#### Features
- **Program and School Page Development:**
  - Completed the development of the Program Page, incorporating detailed features such as program name, capacity, technical requirements, cost per person, runtime options, and booking statuses.
  - Enhanced the School Page with functionalities for better management and accessibility, focusing on integrating teacher information into the booking records.

#### Technical Updates
- **Backend and Frontend Integration:**
  - Implemented comprehensive error handling and data validation across the platform to improve reliability and user experience.

#### Code Reviews and Testing
- **Enhanced Code Review Process:**
  - Strengthened our code review practices, ensuring that every pull request undergoes thorough scrutiny for quality assurance before merging.
  - Focused on peer reviews to foster a collaborative environment and enhance code quality.

- **Deployment and Testing Challenges:**
  - Addressed initial deployment issues by revising our deployment strategies and enhancing server configurations.
  - Initiated manual testing protocols due to temporary setbacks with automated testing tools, ensuring all features function as expected despite these challenges.

#### Challenges and Fixes
- **Technical Setbacks in Automated Testing:**
  - Continued to face challenges with automated testing setups, impacting our ability to perform seamless CI/CD operations.
  - Actively worked on troubleshooting and optimizing our automated testing framework to ensure integration into our development workflow.

#### Documentation and Cleanup
- **Documentation Enhancement:**
  - Updated and refined project documentation, including API documentation and user manuals, to aid in usability and maintenance.
  - Documented all new features and updates thoroughly to ensure clear understanding and easy onboarding for new team members or external developers.

#### Decisions Made
- **Technology Stack Adjustments:**
  - Agreed on adopting additional tools and technologies to address ongoing challenges and improve our development process.
  - Decided to continue enhancing the frontend with additional Vue.js features to improve interactivity and responsiveness.

#### Next Steps
- Resolve the remaining issues with automated testing to fully restore our CI/CD pipeline.
- Begin preparations for Sprint 3, focusing on advancing user engagement features and expanding the platform’s capabilities.

This changelog represents our efforts to not only expand our project’s features but also to refine our processes and overcome technical challenges. We remain committed to delivering a high-quality product and improving our operations continuously.



## Sprint 3 

### Trello Link:https://trello.com/invite/b/cxmdL565/ATTI791cc23ecbab98e23c85bfee0ffa2900A5CC9676/sg-booking-system-sprint-3

### What we will do:
In Sprint 3, our goal is to develop core functionalities of the booking management system, including booking request management, to-do checklist, and reporting of booking data. By the end of this sprint, we aim to enable science gallery staff to efficiently manage booking requests from school teachers, by developing features including create, view, edit and delete bookings. We also target to complete the to-do list features. These functionalities have high priority and can help our client streamline the booking management process, improving operational efficiency. Additionally, we will implement features for managing the program list to ensure the system is flexible for future changes in programs. If time allows, we will discuss with client and implement some of the could-have features.



## Sprint 4

### Trello Link:  https://trello.com/invite/b/WdrvUMHN/ATTIe982d4271b510a602886d9423c37ae2bDC59BFC0/sg-booking-system-sprint-4

### What we will do:
In Sprint 4，our goal is to connect the front-end with the back-end database and deploy it. As well as improve the remaining functions such as adding, deleting, checking and changing the booking page, as well as exporting.After refining the functionality and completing the database connection, the database is deployed to the cloud database. And deploy the website from local to cloud so that users can use our project directly through the website address.





